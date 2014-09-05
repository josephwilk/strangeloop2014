## Hacking Overtone

This page includes tips and tricks for getting around the Overtone code base.

### Where the eff is X defined?!

`overtone.core` and `overtone.live` use a function (`overtone.util.ns/immigrate`) to suck all the Overtone vars into one namespace. This is great for using Overtone, but might leave you scratching your head about where the actual code is. Luckily, all the vars have the originating namespace in their metadata:

```clj
user=> (meta #'overtone.core/metronome)
{:arglists ([bpm]),
:ns #<Namespace overtone.core>,
:name metronome,
:orig-ns overtone.music.rhythm,
:doc
"A metronome is a beat management function. ...",
:line 73,
:file "overtone/music/rhythm.clj"}
```

or you could just grep for it.

