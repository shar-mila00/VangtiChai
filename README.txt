Assignment 1: VangtiChai
==================================================================

DESCRIPTION
VangtiChai lets you enter a money amount with a numeric keypad implemented
from scratch and shows the change for that amount in Taka notes of
500, 100, 50, 20, 10, 5, 2 and 1 (greedy breakdown).

Tapping a digit appends it to the current amount from the right: typing
2, 3, 4 in order shows 2, then 23, then 234. CLEAR resets the amount.
When the device is rotated, the currently entered amount and the change
list are preserved (state is saved in onSaveInstanceState).

LANGUAGE / API
- Language: Kotlin
- Minimum API level: 21 (Android 5.0 Lollipop)
- Target API level: 34
- Layouts: ConstraintLayout in all four variants

LAYOUT VARIANTS (resource qualifiers)
- layout/                  phone portrait   (tested on Pixel XL, 411x731dp)
- layout-land/             phone landscape  (tested on Pixel XL, 731x411dp)
- layout-sw600dp/          tablet portrait  (tested on Nexus 10, 800x1280dp)
- layout-sw600dp-land/     tablet landscape (tested on Nexus 10, 1280x800dp)

The phone and tablet variants share the same view ids, so the single
MainActivity serves all four configurations; only dimensions change
between phone and tablet through values-sw600dp/sizes.xml.

NO HARDCODED LAYOUT VALUES
no text sizes, paddings or margins are hardcoded in
the layout XML files. All of them are defined as dimensions:
- res/values/sizes.xml           (phone values)
- res/values-sw600dp/sizes.xml   (tablet overrides)
All strings live in res/values/strings.xml and all colors in
res/values/colors.xml, referenced by @.../ resources from the layouts.

HOW TO BUILD AND RUN
1. Open the project folder in Android Studio (Hedgehog/Koala or newer).
2. Let Gradle sync (an internet connection is needed on first sync).
3. Create emulators for a Pixel XL phone and a Nexus 10 tablet
   (any system image with API 21+ works).
4. Run the app; rotate the emulator (Ctrl+Left/Right arrows or the
   rotate button) to check both orientations on each device.

TESTED DEVICES / SCREENS
- Pixel XL (411x731dp): portrait and landscape
- Nexus 10 (800x1280dp): portrait and landscape

