package zr.example.netty.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import zr.example.netty.common.SerializerAlgorithmEnum;

/**
 * @description: FastJson格式序列化
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public class JDKSerializer implements Serializer {

  @Override
  public byte getSerializerAlgorithm() {
    return SerializerAlgorithmEnum.JDK.getCode();
  }
  
  @Override
  public byte[] serialize(Object object) {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      ObjectOutputStream oout = new ObjectOutputStream(out);
      oout.writeObject(object);
      return out.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public <T> T deSerialize(Class<T> clazz, byte[] bytes) {
    try(ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
      ObjectInputStream oin = new ObjectInputStream(in);
      return (T) oin.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

}
