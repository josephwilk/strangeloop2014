.PHONY: all


all:
	@echo "> Preparing for the adventure..."
	@echo ""
	git submodule update --init --recursive
	cd overtone/strangeloop && ./lein deps
	@echo ""
	@echo "> You are ready, go forth..."
