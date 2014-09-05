# Instruments

```
      _______________
 :) _|              |
 █-[__________ _____|
][\.  ][         ][
```

You will want some kind of IDE which is capable of sending code to a REPL instance.

There are many paths to getting this setup.
The most walked make our journey easier,
but there is nothing stopping you going off on your own.

### Vim

Plugins for interacting with a REPL with Clojure:

* [vim-clojure-static](https://github.com/guns/vim-clojure-static)
* [vim-fireplace](https://github.com/tpope/vim-fireplace)
* [vim-classpath](https://github.com/tpope/vim-classpath)


### Emacs

NOTE: if you want to use emacs-live ensure `emacs --version` >= 24.3

Ubuntu

```
sudo add-apt-repository ppa:cassou/emacs
sudo apt-get update

sudo apt-get install emacs24 emacs24-el emacs24-common-non-dfsg
````

Homebrew

```
brew install emacs --cocoa --srgb
$ brew linkapps Emacs
```

#### Emacs-live

Making an existing emacs ready for live coding very quickly:

```
bash <(curl -fksSL https://raw.github.com/overtone/emacs-live/master/installer/install-emacs-live.sh)
```

### Vanilla Emacs

Ensure Cider && Clojure-mode packages are installed

```
(require 'package)
(add-to-list 'package-archives
  '("melpa-stable" . "http://melpa-stable.milkbox.net/packages/") t)

(package-install 'cider)
(package-install 'clojure-mode))
```

### LightTable

http://www.lighttable.com

Windows / Mac OSX / Linux 32-bit / Linux 64-bit

## Custom instruments

Have a instrument you would like to add?

Make a Pull request.