.PHONY: all


all:
	@echo "> Preparing..."
	@echo ""
	git submodule update --init --recursive
	cd freedom/overtone && ./lein deps
	@echo ""
	@echo "> You can now make sound!"
