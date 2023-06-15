package com.example.mapper;

import com.example.entity.Account;
import com.example.entity.User;
import org.apache.ibatis.annotations.*;

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

    @Update("update db_account set username = #{username}, password = #{password}, email = #{email} where id = #{id}")
    int updateUser(User user);

    @Select("select code from email_code where email = #{email}")
    String findEmailCode(String email);

    @Select("insert into email_code values(#{email}, #{code})")
    int saveEmailCode(String email, String code);

    @Delete("delete from email_code where email = #{email}")
    int deleteEmailCode(String email);
}
