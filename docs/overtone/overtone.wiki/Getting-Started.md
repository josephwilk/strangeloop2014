Before you start, please note that using Overtone is sometimes a difficult startup process for the newcomer.  It will definitely help if you are comfortable with some or all of the following:

* Using a **command line environment** for your system.  You will need to perform basic UNIX or DOS commands to set up a development environment.
* The **[Clojure](http://clojure.org/) language and environment** (e.g. [Leiningen](http://leiningen.org/) and the REPL).
* **Text editors** like Sublime, Emacs, Vi or a developer environment like Eclipse with CounterClockwise.   
* The **[Supercollider](http://supercollider.sourceforge.net/)** audio synthesis environment.  
* The basics of **music theory**.
* The basics of **sound synthesis**.
* **Change** - Overtone is a tool under development.

You can dive in without being comfortable with all of these topics, but understand that it will not be a easy experience until you gain some background in all of these areas.  You should expect to spend some time learning.  Take your time, plan to spend time filling in the areas that are not familiar and enjoy the journey.  If you have questions, the [Google Group](https://groups.google.com/forum/?fromgroups=#!forum/overtone) is a great resource.

Getting Started
===============
OK, so we're assuming that you've already installed Overtone, started a REPL and have connected to an scsynth server. If not, these pages will help you:

* [[Installing Overtone]]
* [[Starting a REPL]]
* [[Connecting scsynth]]

First let's define a synth:

```clj
> (definst foo [] (saw 220))
```

Inst calls the synth macro which takes a synthesizer definition form.  It will compile this form
down to a SuperCollider synthdef, load it onto the synthesis server, and then
return a function that can be used to trigger the synth.  So we just created a
saw wave at 220 hz.

Be careful, this will
be at 100% volume!!!

```clj
> (foo) ; Call the function returned by our synth
#<synth-node[loading]: user/foo 4>      ; returns a synth ID number
> (kill 4) ; kill the synth with ID 4
> (kill foo) ; or kill all instances of synth foo
```

Synth trigger functions return an ID that can be used to modify or kill
instances.

The `saw` function represents a [unit-generator](http://danielnouri.org/docs/SuperColliderHelp/UGens/UGens.html), or [ugen](http://danielnouri.org/docs/SuperColliderHelp/UGens/UGens.html) (sounds like "you jen").  These are the basic building blocks for creating synthesizers, and they can generate or process both audio and control signals.  Check the link for
descriptions of many ugens from the SC documentation.  Also, they all have doc
strings in Overtone.

```
> (odoc saw)
-------------------------
overtone.live/saw
([freq])

  [freq 440.0]

  freq - Frequency in Hertz (control rate).

  Band limited sawtooth wave generator

  Categories: Generators -> Deterministic
  Rates: [ :ar ]
  Default rate: :ar
```

Insts can take arguments:

```clj
> (definst bar [freq 220] (saw freq))
> (bar 110)
5
> (kill bar)
```

you can also trigger multiple synths and kill them all with one kill command:

```clj
> (definst baz [freq 440] (* 0.3 (saw freq)))
> (baz 220)
> (baz 660)
> (kill baz) ; stop all running synths
```

Remember `(stop)`, as it can save you when accidentally trigger a bunch of
synths and you need to kill the audio fast.

```clj
> (foo)
> (bar)
> (baz)
> (stop) ; stop all running synths
```

When this synth is running the saw wave ugen is outputing an audio signal.  This
signal is represented as a continuous stream of floating point values between -1
and 1, so by multiplying by 0.3 we are just lowering the amplitude of this
signal, adjusting the volume.

We can change parameters of the running synth on the fly with `ctl`

```clj
> (definst quux [freq 440] (* 0.3 (saw freq)))
> (quux)
> (ctl quux :freq 660)
```

So far we've just used a single ugen and passed it arguments, but ugens can be
plugged into each other in arbitrary ways too.  Basically anywhere a ugen takes
an argument value it can also take an input signal that will control that value.
Lets put a tremolo on our saw wave:

```clj
> (definst trem [freq 440 depth 10 rate 6 length 3]
    (* 0.3
       (line:kr 0 1 length FREE)
       (saw (+ freq (* depth (sin-osc:kr rate))))))
> (trem)
4
> (kill trem)
> (trem 200 60 0.8)
4
> (kill trem)
> (trem 60 30 0.2)
...
```
The `line` ugen outputs a value from the start value to the end value over a
specific length of time.  In this example we use it as a simple way to stop the
synth after a few seconds.  The last argument, `FREE`, is a typical argument to
this kind of control ugen that tells the ugen to free the whole synth instance
once it completes.  That way you don't have to `kill` it by hand anymore.

Also note that the line and sin oscillator ugens have the `:kr` suffix.  Many of
the ugens can operate at different rates, shown at the bottom of the doc string.
The two primary rates are audio rate and control rate, `:ar` and `:kr`.  Audio rate
runs at the rate of your audio card, and control rate runs at about 1/60 of that
speed, so for signals that are just controlling a value rather than outputting
audio you can save wasted CPU by using `:kr`.