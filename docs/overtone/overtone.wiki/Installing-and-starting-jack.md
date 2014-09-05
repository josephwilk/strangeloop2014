Linux Audio Setup

```sh
sudo apt-get install jack-tools ant openjdk-6-jdk fftw3 qjackctl
```

You'll need to get the [jack audio](http://jackaudio.org) daemon running, and we recommend [qjackctl](http://qjackctl.sourceforge.net) to figure out what command will be best to use.  Then once you have it dialed in you can switch to using the terminal.  For best performance you need to install a realtime enabled kernel, which allows the audio system to get high scheduled immediately when there is data to process.  With purely generative music this isn't such a big deal, but if you want to jam with other instruments or process external sound in realtime then you'll want to invest the effort in setting up an rt-kernel.  Ubuntu studio makes it pretty easy, especially if you aren't experienced in compiling the kernel.  In the meantime, just turn-off the realtime support in the qjacktl options, and the audio server should boot.

You can create a `.jackdrc` file with this command to automatically start the jack server on boot, or you will need to run it manually to start the Jack audio server:
```sh
jackd -r -d alsa -r 44100
```

If you get errors from the above try:
```sh
jackd -r -d alsa -r 44100 -P
```

An alternative is to use the qjackctl gui.

We hope to support ALSA audio in future versions.

If pulseaudio is running while starting jackd, either jackd will not start properly or jackd will mute all other applications. What we might want to do is to connect pulseaudio and jackd in a sequence, demonstrated here (although, it's not completely without problems):
http://www.youtube.com/watch?v=6J-RQudJx30

The safer way is to follow option #4 from http://jackaudio.org/pulseaudio_and_jack

An alternative option, if you don't need pulseaudio running, is to kill pulseaudio and then run jackd. Here's more information: http://askubuntu.com/a/232163/22073

## If you can't start jackd without root rights

jackd needs an appropriate privelege to run. This post details a solution: http://ubuntuforums.org/showthread.php?t=1637399&p=10199399#post10199399

## Note for Fedora Users

It has been reported that the command `jack_lsp` is required and may be obtained by installing the fedora package `jack-audio-connection-kit-example-clients` 