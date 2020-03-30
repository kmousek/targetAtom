package com.ktds.targetatom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.targetatom.TargetatomProcessorApplication;

@RestController
public class RestartController {

	@PostMapping("/restart")
	public void restart() {
		TargetatomProcessorApplication.restart();
	}
}
