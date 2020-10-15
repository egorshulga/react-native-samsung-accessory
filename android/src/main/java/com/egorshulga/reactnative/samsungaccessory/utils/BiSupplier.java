package com.egorshulga.reactnative.samsungaccessory.utils;

@FunctionalInterface
public interface BiSupplier<In1, In2> {
  void apply(In1 a, In2 b);
}
