package com.example.mapper;

import com.example.entity.Account;
import com.example.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author Ikun
 */
@Mapper
public interface UserMapper {
    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);

    @Select("select * from persistent_logins where username = #{username}")
    User findUserByUsername(String username);

    @Select("select * from db_account  where email = #{email}")
    User findUserByUserEmail(String email);


    @Insert("insert into db_account values(#{id},#{email},#{username},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int saveUser(User user);


}
