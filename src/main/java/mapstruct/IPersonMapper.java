package mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author gjd
 */
@Mapper(uses = {AttributeConvertUtil.class})
public interface IPersonMapper {
  IPersonMapper INSTANCT = Mappers.getMapper(IPersonMapper.class);

  //  @Mapping(target = "userNick1", source = "userNick")
  @Mapping(target = "userNick1", source = "userNick")
  @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd")
  @Mapping(target = "age", source = "age", numberFormat = "#0.00")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userVerified", defaultValue = "defaultValue-2")
  @Mapping(target = "attributes", source = "attributes", qualifiedByName = "json2Object")
  UserEntity po2entity(UserPo userPo);
}
