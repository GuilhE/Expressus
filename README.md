<img src="/media/icon.png" width="100" align="right">

# Expressus
[![Featured in Kotlin Weekly - Issue #299](https://img.shields.io/badge/Featured_in_Kotlin_Weekly-Issue_%23299-7878b4)](https://mailchi.mp/kotlinweekly/kotlin-weekly-299) [![Featured in Kotlin Weekly - Issue #301](https://img.shields.io/badge/Featured_in_Kotlin_Weekly-Issue_%23301-7878b4)](https://mailchi.mp/kotlinweekly/kotlin-weekly-301) [![Featured in Kotlin Weekly - Issue #304](https://img.shields.io/badge/Featured_in_Kotlin_Weekly-Issue_%23304-7878b4)](https://mailchi.mp/kotlinweekly/kotlin-weekly-304)  
[![Android Weekly](https://androidweekly.net/issues/issue-515/badge)](https://androidweekly.net/issues/issue-515)

KMM sample project acting as a playground to illustrate what's discussed in these articles: 
<p>
 <a href="https://guidelgado.medium.com/36d84056c616"><img src="/media/banner1.png"></a></br>
 <a href="https://guidelgado.medium.com/a67bd9a49882"><img src="/media/banner2.png"></a>
</p>

## Details

### Shared
- Model-View-Intent architecture
- Finite State Machine to validate state transitions
- State restoration (both for UI State and FSM State)
- Compose for Desktop used in __shared-ui__ module to share composables between Desktop and Android

### Platforms
  
#### Mobile
- UI State emissions using Kotlin Flow and Swift Combine Publisher
- Jetpack Compose (+Desktop) and SwiftUI

`./gradlew :androidApp:installDebug`

#### Desktop
- UI State emissions using Kotlin Flow
- Compose for Desktop

`./gradlew :desktopApp:run`

## Outputs

### UI
<p align="center">
    <a href="https://user-images.githubusercontent.com/2677139/167465046-1226fa4f-8ddf-4c3d-bd0e-8f0051cac4c0.mp4"><img src="/media/ui-android.png" height="500"></a>
    <a href="https://user-images.githubusercontent.com/2677139/167465066-8b88f998-e327-4b11-9986-98b647be4e47.mov"><img src="/media/ui-ios.png" height="500"></a>
    <a href="https://user-images.githubusercontent.com/2677139/167465082-1cce53d8-0fc2-4125-a341-7daf9741c067.mov"><img src="/media/ui-desktop.png" height="500"></a>
</p>

### FSM + MVI
<p align="center">
 <img src="/media/fsm-android.gif" width="390">  <img src="/media/fsm-ios.gif" width="430">   
</p>
<p align="center">Click on each image for full resolution</p>

 ## LICENSE

Copyright (c) 2022-present GuilhE

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
