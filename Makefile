.PHONY: all


all:
	@echo "> Preparing..."
	@echo ""
	git submodule update --init --recursive
	cd overtone/strangeloop && ./lein deps
	@echo ""
	@echo "> You can now make sound!"
