.PHONY: all

all:
	@echo "> It all begins staring at a computer screen."
	@echo ""
	git submodule update --init --recursive
	cd freedom/overtone && ./lein deps
	@echo ""
	@echo "> Do you seek power, freedom or both?"
	@echo "                                      ¸¸.•*¨*•♫♪"
