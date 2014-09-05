# Creating Screencasts with Overtone

Instructions on how to create screencasts that incorporate Overtone audio.

## Mac

If you're happy to pay for software, [ScreenFlow](http://www.telestream.net/screenflow/overview.htm) works perfectly with Overtone. Otherwise the following is a free alternative:

Adapted from
http://www.soundpimp.com/guidelines/computer-audio-enhancer-mac-osx/
following the "Alternative 1 - Audio routing setup using Jack"
Tested May, 2013 on OSX 10.7.5

1. Get Jack from http://www.jackosx.com/

1. Put on headphones to stop any feedback from Speakers to the Built-in
  Mic.

1. Always start Jack first to ensure proper detection of the audio
  applications.

1. Click Start in the JackPilot.

1. Now JackRouter becomes visible as an output playback device in Audio
  Midi Setup.

1. Select JackRouter as the default device for sound output using gear
  at bottom of list.

1. Click Routing in the JackPilot, this opens the Jack Connection
  Manager.

1. Start the Clojure REPL and boot Overtone.

1. Start Quicktime Player and File -> New Screen Recording.  Turn the
  volume slider in the dialog all the way up and select JackRouter as
  input.

1. Play some sound `(demo (sin-osc [400 400]))` and verify that there is
  sound via Built-in Output.  Just to be sure we're working...

1. Create the audio routing in the Jack Connection Manager.  Selections
  are done by:
  -- single clicking on Send Ports to mark them blue
  -- double clicking on Receive Ports to make them red (active).

9. Single-click on the "Java" listed under Send Ports (Blue highlight)

9. Double-click on the "Quicktime Player" listed under Receive Ports
  (Red text)

9. Keep the "System:capture" connections in order to hear the sounds.

9. Back to Overtone.  A `(demo (sin-osc [400 400]))` should make a
  sound that you can hear, and if you look at the screen recording
  monitor, you will see it.

9. If you talk, you can also see your voice is being recorded from the
  external Mic.

9. Now click the record button in Quicktime Player to start Your Screen
  & Audio Capture.

9. Here is where you live-code and create your sounds and explain how to do crazy-awesome things with Overtone.

9. And then you're done...
  - Quit Qvertone
  - Quit QuickTime Player
  - Click Stop Jack

Now you can listen to the recorded movie, edit and prepare it for uploading to your favorite video-sharing site.

## Other Platforms

Please add instructions if you can.