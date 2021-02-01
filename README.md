# Тестовое задание
Разработать "заглушку" на java. Выбор фреймворков - любой.
gradle/maven - главное чтобы была конфигурация, собирающая испольняемый fat-jar.


## Методы
* ``get("/users")``   
  ***Описание***: Возвращает список всех пользователей в формате  
  ```json
  [  
      {"username": "значение", "password":"значение"},
      {"username": "значение2", "password":"значение2"}
  ]
  ```
  *Опционально*: Может быть задан query параметр userNameMask - в виде регулярного выражения, и тогда нужно вернуть
только пользователей с username соответствующим regex.
  #### Условия выполнения
  Если пользователи не найдены - отдать пустой список []  
  
* ``put("/user")``  
  ***Описание***: Принимает body в формате {"username": "значение", "password":"значение"},
сохраняет пользователя в список пользователей.  
  #### Условия выполнения
  Если пароль пустой или не указан - 500.  
  Eсли пользователь с таким username уже существует - отдает ошибку с 500 статус кодом
* ``post("/updatePassword")``  
  Принимает body в формате
  ```json
  {
      "username": "значение", 
      "oldpassword":"старый пароль", 
      "password":"значение"
  }
  ```    
  #### Условия выполнения  
  Eсли пользователь существует и его текущий пароль равен значению поля "oldpassword" то обновляет его пароль и отдает 200 ОК.   
  Eсли пользователь с таким userName не существует - ошибка 500.  
  Eсли значение поля "oldpassword" не совпадает с текущим паролем пользователя - 500

## Остальные условия
* Пользователей просто хранить в памяти.
* Все поля в json - строки
* *Опционально:* Ответы с ошибками дополнять сообщением о причине ``{"error":"description"}``
* *Опционально:* Покрытие автотестами.
