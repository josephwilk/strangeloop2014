For the best live coding experience in emacs, you'll want to install:

* Leiningen 2
* Emacs Live


### Leiningen 2
See https://github.com/technomancy/leiningen/wiki/Upgrading

    $ wget -O ~/bin/lein2 https://raw.github.com/technomancy/leiningen/preview/bin/lein
    $ chmod 755 ~/bin/lein2

### Emacs Live
See https://github.com/overtone/emacs-live#getting-started

    bash <(curl -fksSL https://raw.github.com/overtone/emacs-live/master/installer/install-emacs-live.sh)

### Pulling it all together

* `cd` into a directory containing a lein project which references overtone as a dependency. 
* Fire up nrepl: `lein repl`

  Look for the line `nREPL server started on port 55803`
  Note that the actual port number may be quite different, remember yours.

* Fire up Emacs
* Connect Emacs to nrepl: `M-x nrepl` or `M-x cider` (in newer versions of Emacs Live)

  At the `Host:` prompt, type `localhost`

  When the prompt `Port:` appears, enter the nREPL port number, from above.

* Fire up Overtone:

```clj
user> (use 'overtone.live)
          _____                 __
         / __  /_  _____  _____/ /_____  ____  ___
        / / / / | / / _ \/ ___/ __/ __ \/ __ \/ _ \
       / /_/ /| |/ /  __/ /  / /_/ /_/ / / / /  __/
       \____/ |___/\___/_/   \__/\____/_/ /_/\___/

                          Programmable Music. v0.9.1


Hello User, may this be the start of a beautiful music hacking session...
nil
user> (demo (sin-osc))

```