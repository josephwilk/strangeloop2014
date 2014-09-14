# Sonic Pi

Download the DMG file: http://sonic-pi.net/get-v2.0 and install.

Launch SonicPi

## Live coding

To make changes on the fly to your code and have those apply without stopping the music requires using:
* a function (created with `define`)
* a thread (`in_thread`) looping calling that function

For example:

```ruby
define :drums do
  6.times do
    sample :drum_heavy_kick, rate: 0.8
    sleep 0.5
  end
end

in_thread(name: :drummers) do
  loop{drums}
end
```

## Examples

Sonic-pi comes with lots of examples to get you started quickly:

[](/docs/sonic-pi/examples_screen.jpg)