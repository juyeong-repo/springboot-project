package com.board.domain;

import java.time.LocalDateTime;

import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {


	private PaginationInfo paginationInfo;

	private String deleteYn;

	private LocalDateTime insertTime;

	private LocalDateTime updateTime;

	private LocalDateTime deleteTime;

}
