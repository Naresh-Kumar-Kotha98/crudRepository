package com.example.util;

import org.springframework.data.domain.Sort;

import com.example.crudRepository.model.SortInputs;

public class SortUtil {

	public static Sort getSortPattern(SortInputs inputs) {
		return Sort.by(Sort.Order.asc(inputs.getSort()[0].getField()));
	}
}
