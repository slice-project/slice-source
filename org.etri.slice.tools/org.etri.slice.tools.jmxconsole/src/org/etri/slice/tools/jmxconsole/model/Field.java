package org.etri.slice.tools.jmxconsole.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * key, value ������ �����͸� ���� ��, ���̺� Input �����ͷ� �����
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
public class Field {
	private String key;
	private Object value;
}
