#!/usr/bin/make -f
SHELL = bash

SERVICES_DIR=service
COMPOSE=docker-compose

up:
	@for service in $(shell ls $(SERVICES_DIR)); do \
		$(COMPOSE) -f $(SERVICES_DIR)/$$service/docker-compose.yml up -d; \
	done

down:
	@for service in $(shell ls $(SERVICES_DIR)); do \
		$(COMPOSE) -f $(SERVICES_DIR)/$$service/docker-compose.yml down; \
	done

up-service:
	@if [ -z "$(SERVICE)" ]; then \
		echo "Usage: make up-service SERVICE=<service_name>"; \
	else \
		$(COMPOSE) -f $(SERVICES_DIR)/$(SERVICE)/docker-compose.yml up -d; \
	fi

down-service:
	@if [ -z "$(SERVICE)" ]; then \
		echo "Usage: make down-service SERVICE=<service_name>"; \
	else \
		$(COMPOSE) -f $(SERVICES_DIR)/$(SERVICE)/docker-compose.yml down; \
	fi