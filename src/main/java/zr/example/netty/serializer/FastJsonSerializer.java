package zr.example.netty.serializer;

import com.alibaba.fastjson.JSON;

import zr.example.netty.common.SerializerAlgorithmEnum;

/**
 * @description: FastJson格式序列化
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public class FastJsonSerializer implements Serializer {

  @Override
  public byte getSerializerAlgorithm() {
    return SerializerAlgorithmEnum.JSON.getCode();
  }

  @Override
  public byte[] serialize(Object object) {
    return JSON.toJSONBytes(object);
  }

  @Override
  public <T> T deSerialize(Class<T> clazz, byte[] bytes) {
    return JSON.parseObject(bytes, clazz);
  }

}
