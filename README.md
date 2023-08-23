# Textify

## Overview/Purpose
Textify is a Kotlin-based application designed to convert visual content into editable text. With Textify, users can capture photos and transform them into text which can then be edited, customized, and shared. The app also provides functionality to convert previously captured images.

### Suitable For:

- **Students**: Capture and convert lecture notes or study materials.
- **Professionals**: Transcribe meetings or presentations without manual transcription.
- **Journalists**: Convert visual content into textual form for articles or reports.
- **Researchers**: Convert printed resources or notes into digital text.
- **Everyday Users**: Transform image-based information into text for various needs.

**Special Note**: Textify supports multiple languages, with a special focus on the Maori language. However, users might occasionally face issues with macron interpretation.

## Development

### Dependencies & Libraries
- **Firebase MLKit**: Used for image-to-text conversion.
- **Camerax**: Implemented in-app camera functionality.
- **RecyclerView**: For the list view of saved images.

### Resources & Learning
- Googlecodelabs for Firebase MLKit.
- Google documentation on Camerax and RecyclerView.
- Online platforms, including Youtube, for tutorials on various features like language switch, backPress, and animations.
- The application supports Android 12 and 13.

### Technical Overview
- **Activities**:
  - MainActivity
  - CameraActivity (No Action Bar)
  - EditVisionActivity
  - Preview
  - SavedImages
  
- **Android Sharesheet**: Used for sharing text and image. (NOTE: There's a known issue with sharing images alongside text for Facebook Messenger).
  
- **SavedImages Activity**: Incorporates a RecyclerView to display previously saved images. Each image can be clicked to initiate the image-to-text conversion.

- **Widgets Used**:
  - Buttons, Switch, TextView, RadioButton, PreviewView, EditText, ImageView, ScrollView, ProgressBar, CardView, FloatingActionButton.

- **Layouts**: Combination of LinearLayout, ConstraintLayout, and FrameLayout.

- **Orientation**: The app supports both portrait and landscape views. A separate layout resource is provided for the activity_main in both orientations.

- **Localization**: Default string resources are provided in English, with Hindi as a second language. An in-app language switch is also provided, but it restricts changing the app's language externally.

- **Feedback & Interactions**:
  - Toast message on successful image capture.
  - DialogBoxes for language change, image processing, and image sharing.

- **Animations**: Four animations xml files (slide_in_left, slide_in_right, slide_out_left, slide_out_right) housed under 'anim' and implemented throughout the app using a helper `Animate` class.

- **Image Handling**: Utilized Glide for efficient image display in RecyclerView, ensuring smooth scrolling.

## Gallery

![Screenshot_20230815-114312](https://github.com/praboberoi/Textify/assets/89440036/3eed46c9-8a1c-4c0d-ba4d-496a2ef6ca92)
![Screenshot_20230815-114337](https://github.com/praboberoi/Textify/assets/89440036/a50134a0-576d-489c-bc03-6e8f7e1aa281)
![Screenshot_20230815-114351](https://github.com/praboberoi/Textify/assets/89440036/5aa95857-aa5c-4b81-bb7c-c193c09df68b)
![Screenshot_20230815-114403](https://github.com/praboberoi/Textify/assets/89440036/28de3a49-e47e-4aea-977d-e2c2262f0c41)
![Screenshot_20230815-114414](https://github.com/praboberoi/Textify/assets/89440036/b44bc4b3-f75b-459c-a8e5-3c74b36b1732)
![Screenshot_20230815-114433](https://github.com/praboberoi/Textify/assets/89440036/3f5a5adb-7f7c-4053-8163-f2a015e865fe)
![Screenshot_20230815-114525](https://github.com/praboberoi/Textify/assets/89440036/fd1aad34-91a2-4a3b-8e31-6505fa5168b0)
![Screenshot_20230815-114546](https://github.com/praboberoi/Textify/assets/89440036/91e1dc70-28a0-4850-a99f-c40cee6f305e)
![Screenshot_20230815-114558](https://github.com/praboberoi/Textify/assets/89440036/5ef113a9-d221-45bb-8915-2c6637f8bced)
![Screenshot_20230815-114609](https://github.com/praboberoi/Textify/assets/89440036/ee7a4cf9-7ed2-4859-8d4b-dad4b2995159)



## License
Apache 2.0

## Contributors
Prableen Oberoi
