package com.example.demo.common;

/**
 * @author cy.W
 * @date 2022-10-12 10:06:57
 *
 */
public interface Enums {

	/**
	 * 用户状态
	 * 
	 * @author cy.W
	 * @date 2022-10-12 10:20:58
	 *
	 */
	enum USER_STATE {

		/** 初始化 */
		INIT(0),
		/** 正常 */
		NORMAL(1),
		/** 锁定 */
		LOCKED(2),
		/** 已删除 */
		DELETED(3);

		private final int state;

		USER_STATE(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}
}
