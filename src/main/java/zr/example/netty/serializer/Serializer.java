package zr.example.netty.serializer;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public interface Serializer {

  /**
   * 获取序列化算法
   *
   * @return
   */
  byte getSerializerAlgorithm();

  /**
   * 序列化
   *
   * @param object
   * @return
   */
  byte[] serialize(Object object);

  /**
   * 反序列化
   *
   * @param clazz
   * @param bytes
   * @return
   */
  <T> T deSerialize(Class<T> clazz, byte[] bytes);

}
