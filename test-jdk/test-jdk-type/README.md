java中获取泛型参数需要用

`((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName()`