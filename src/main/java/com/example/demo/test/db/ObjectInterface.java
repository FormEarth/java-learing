package com.example.demo.test.db;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author cy.W
 * @date 2022-5-30 15:23:34
 *
 */
//@Mapper
public interface ObjectInterface {

	@Insert("insert into t_product_attr_property (`attr_property`, `attr_property_web`, `product_id`, `directory_id`,"
			+ "`property_type`, `property_order`,`is_read_only`, `remark`, `property_length`, `action_dict`) values(#{attribute.attrProperty}, #{attribute.attrPropertyWeb},"
			+ " #{attribute.productId}, #{attribute.directoryId}, #{attribute.propertyType}, #{attribute.propertyOrder}, #{attribute.isReadOnly} ,#{attribute.remark}, #{attribute.propertyLength}, #{attribute.actionDict})")
	public int insert(@Param("attribute") ProductAttrProperty attribute);
	
	@Delete("delete from t_product_attr_property where directory_id = 1000")
	public int deleteAll();

	// INSERT INTO `iot-nms`.`t_product_attr_property` (`id`, `attr_property`,
	// `attr_property_web`,
	// `product_id`, `directory_id`, `property_type`, `property_order`,
	// `property_length`, `action_dict`,
	// `is_read_only`, `is_active`, `active_index_start`, `active_index_end`,
	// `active_placeholder`)
	// VALUES ('1', 'LEVEL', 'level', '45470259dfd14a788dbcaf50e9aa0fdb', '1',
	// 'input', '1', '1',
	// 'label_dict', '0', '0', '0', '1', '_');

}
