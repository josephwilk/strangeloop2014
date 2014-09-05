## Installation

For the best live coding experience in Vim, you'll want to install:

* [vim-clojure-static](https://github.com/guns/vim-clojure-static)
* [vim-fireplace](https://github.com/tpope/vim-fireplace)
* [vim-classpath](https://github.com/tpope/vim-classpath)

Now:

* In one shell session, start a REPL using `lein repl` from the root directory of your Overtone project. It is important to do this because otherwise Overtone will block any keyboard input to Vim. (If that happens, you may be able to hit `ctrl+c` to close that Overtone process.)
* In Vim, start writing your Overtone file. It tends to work better if you use `overtone.live` rather than `overtone.core`.
* The first time you want to hear some output, save the file and use `:Require` to load it into Overtone. You will also need to do this any time you define a new instrument, etc.
* After the file has been loaded once, you can type `cpp` to evaluate the inner-most expression at the cursor.
* You can also evaluate a single line with `:Eval` (in normal mode), or select multiple lines from visual mode and `:Eval` to run all of them.
* If you have problems, try reloading the entire file and dependencies with `:Require!`. This may take a few seconds.
* For more info on these commands, see the [fireplace README](https://github.com/tpope/vim-fireplace).