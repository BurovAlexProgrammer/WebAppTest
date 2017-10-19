/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package ru.hostco.burovalex.webapptest.services;

import java.util.List;

import ru.hostco.burovalex.webapptest.entity.Todo;

public interface TodoListService {

	/** get Todo list **/
	List<Todo> getTodoList();
	
	/** get Todo by id **/
	Todo getTodo(Integer id);
	
	/** save Todo **/
	Todo saveTodo(Todo todo);
	
	/** update Todo **/
	Todo updateTodo(Todo todo);
	
	/** delete Todo **/
	void deleteTodo(Todo todo);
	
}
