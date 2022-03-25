package com.db.awmd.challenge.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.awmd.challenge.domain.TransferDomain;
import com.db.awmd.challenge.domain.TransferResponseDomain;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.service.AccountsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/transfer")
@Api(tags = {"Transfer Controller"}, description = "Provide APIs for transaction related operation")
public class TransferController {

private static final Logger log = LoggerFactory.getLogger(TransferController.class);
	
	@Autowired
	private AccountsService accountService;
	
	@PostMapping(consumes = { "application/json" })
	@ApiOperation(value = "API to create transaction", response = TransferResponseDomain.class, produces = "application/json")
	public ResponseEntity transferMoney(@RequestBody @Valid TransferDomain request) throws Exception {

		try {
			accountService.transferBalances(request);
			
			TransferResponseDomain result = new TransferResponseDomain();
			
			return new ResponseEntity<>(result.getMessage(), HttpStatus.ACCEPTED);
		} catch (DuplicateAccountIdException de) {
			log.info("Duplicate Account Detected.");
		}
		catch (Exception e) {
			log.info("Transaction processing failed");
		}
		return ResponseEntity.status(500).build();
	}
}
