#!/bin/bash

# DEFAULT VALUES
kmp_module="shared-ui-compose"
ios_app="iosApp"
target_name="iosApp"
#####################

xcodeproj_path="$ios_app.xcodeproj"
files_source="$kmp_module/build/generated/ksp/"
group_name="SharedRepresentables"
files_destination="$ios_app/$group_name/"
copied_count=0

check_for_xcodeproj() {
  if ! gem spec xcodeproj > /dev/null 2>&1; then
    echo "Installing 'xcodeproj' gem..."
    gem install xcodeproj
  fi
}

copy_files() {
  local files_source="$1"
  local files_destination="$2"

  while IFS= read -r -d '' file; do
    destination_file="$files_destination/$(basename "$file")"
    if [ ! -f "$destination_file" ] || ! cmp -s "$file" "$destination_file"; then
      rsync -a --checksum "$file" "$files_destination"
      files_copied+=("$file")
    fi
  done < <(find "$files_source" -type f -name '*.swift' -print0)
  copied_count=${#files_copied[@]}
}

add_file_references() {
  local xcodeproj_path="$1"
  local target_name="$2"
  local group_name="$3"

  ruby_script='
    require "xcodeproj"

    xcodeproj_path = ARGV[0]
    target_name = ARGV[1]
    group_name = ARGV[2]

    files_to_copy = Dir.glob("#{group_name}/*")
    if files_to_copy.empty?
      puts "> No files to copy. Exiting"
      exit
    end

    xcodeproj = Xcodeproj::Project.open(xcodeproj_path)
    group = xcodeproj[group_name] || xcodeproj.new_group(group_name)
    target = xcodeproj.targets.find { |t| t.name == target_name }

    existing_file_references = target.source_build_phase.files_references
    files_added = false

    files_to_copy.each do |file_path|
      unless existing_file_references.any? { |file_ref| file_ref.path == file_path }
        file_reference = group.new_file(file_path)
        puts "> #{file_reference} added!"
        target.add_file_references([file_reference])
        files_added = true
      end
    end

    unless files_added
      puts "> No new references to copy"
    end

    xcodeproj.save
  '
  ruby -e "$ruby_script" "$xcodeproj_path" "$target_name" "$group_name"
}

check_for_xcodeproj
echo "> Copying files to $files_destination"
copy_files "$files_source" "$files_destination"
if [ "$copied_count" -gt 0 ]; then
  echo "> Checking for new references to be added to xcodeproj"
  cd iosApp || exit
  add_file_references "$xcodeproj_path" "$target_name" "$group_name"
  echo "> Done"
else
  echo "> No files were copied, skipping reference check"
fi