#!/bin/bash

ruby_script='
require "xcodeproj"

xcodeproj_path = ARGV[0]
target_name = ARGV[1]

xcodeproj = Xcodeproj::Project.open(xcodeproj_path)
group_name = "SharedRepresentables"
group = xcodeproj[group_name] || xcodeproj.new_group(group_name)
target = xcodeproj.targets.find { |t| t.name == target_name }

existing_file_references = target.source_build_phase.files_references

Dir.glob("#{group_name}/*").each do |file_path|
  unless existing_file_references.any? { |file_ref| file_ref.path == file_path }
    file_reference = group.new_file(file_path)
    puts "> #{file_reference} added!"
    target.add_file_references([file_reference])
  end
end

xcodeproj.save
'

if ! gem spec xcodeproj > /dev/null 2>&1; then
  echo "Installing 'xcodeproj' gem..."
  gem install xcodeproj
fi

echo "> Copying generated files to iosApp"
find shared-ui-compose/build/generated/ksp/ -type f -name '*.swift' -exec rsync -a --checksum {} iosApp/SharedRepresentables/ \;
echo "> Adding references to xcodeproj"
cd iosApp || exit
ruby -e "$ruby_script" "iosApp.xcodeproj" "iosApp"
echo "> Done"