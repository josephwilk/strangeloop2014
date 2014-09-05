Overtone provides two simple functions to allow you to record audio: `recording-start` and `recording-stop`.  These are used as follows:

```clj
(recording-start "~/Desktop/foo.wav")
;; make some noise. i.e.
(demo (pan2 (sin-osc)))
;; stop recording
(recording-stop)
```

You'll now see a file at `~/Desktop/foo.wav` which will contain the audio you generated.

## Saving buffers

If you have a buffer containing audio you wish to save to disk, you can do this with the `buffer-save` function:

```clj
(buffer-save b "~/Desktop/bong.wav") 
```