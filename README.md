# Baklol Dialer — phone-first build project

This is a starter Android project that wraps the premium HTML prototype in Android WebView and includes an initial Android Telecom InCallService integration.

## Build from your phone using GitHub Actions
1. Extract this ZIP in your phone's Files app (ZArchiver can extract ZIP files).
2. In your GitHub repository, upload the *contents* of this folder, not the ZIP itself. Keep `.github/workflows/build.yml` and the `app/` folder at the repository root.
3. Open the repository's Actions tab, choose **Build Baklol Dialer APK**, then **Run workflow**.
4. When successful, download the `Baklol-Dialer-debug-APK` artifact.

## Important limitations
- This is an early starter project, not a fully tested replacement for the stock dialer.
- Android default-dialer role eligibility and call UI behaviour must be tested on a real device. The starter has a basic native call screen and the HTML preview.
- Recording is not implemented. Android/device/carrier restrictions may prevent call recording.
- A debug APK is for testing, not Play Store release. Publishing requires a signed release AAB and policy review.
