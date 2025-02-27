package com.ub.org.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.org.demo.view.Download;

public interface DownloadRepository extends JpaRepository<Download, Long> {

}
