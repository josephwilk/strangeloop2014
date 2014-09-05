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

### Emacs-live(24.3)

`emacs --version #=> 24.3`

```
bash <(curl -fksSL https://raw.github.com/overtone/emacs-live/master/installer/install-emacs-live.sh)
```

### Vanilla Emacs

(add-to-list 'package-archives '("marmalade" . "http://marmalade-repo.org/packages/"))

package-install cider
package-install clojure-mode

### LightTable

http://www.lighttable.com

Windows / Mac OSX / Linux 32-bit / Linux 64-bit

## Custom weapons

Have a weapon you would like to add? 

Make a Pull request.
