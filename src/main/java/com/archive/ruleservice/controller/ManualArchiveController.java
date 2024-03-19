package com.archive.ruleservice.controller;

import com.archive.ruleservice.scheduler.ArchiveDeleteRunner;
import com.archive.ruleservice.scheduler.ArchiveRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Hidden API to run archive job manually, just for demo
 */
@RestController
@RequestMapping("/archive/manual")
@RequiredArgsConstructor
public class ManualArchiveController {
    private final ArchiveRunner archiveRunner;

    private final ArchiveDeleteRunner archiveDeleteRunner;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> manualArchive() throws SQLException {
        archiveRunner.executeArchive();
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @DeleteMapping("/archieveddata")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteArchiveData() throws SQLException {
        archiveDeleteRunner.executeDeleteArchiveData();
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
