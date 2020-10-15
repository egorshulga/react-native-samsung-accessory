package com.egorshulga.reactnative.samsungaccessory.constants;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class SAConstantsModule extends ReactContextBaseJavaModule {
  @Override
  public String getName() {
    return "SAConstants";
  }

  public SAConstantsModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public Map<String, Object> getConstants() {
    Map<String, Object> constants = new HashMap<>();

    addStaticFields(constants, Feature.class);
    addStaticFields(constants, Transport.class);
    addStaticFields(constants, Event.class);
    addStaticFields(constants, PeerStatus.Search.class);
    addStaticFields(constants, PeerStatus.Availability.class);

    return constants;
  }

  private static void addStaticFields(Map<String, Object> constants, Class<?> clazz) {
    for (Field field : clazz.getFields()) {
      int modifiers = field.getModifiers();
      if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
        try {
          constants.put(field.getName(), field.get(null));
        } catch (IllegalAccessException e) {
          System.out.println(String.format("Unable to access field %s.%s", clazz.getName(), field.getName()));
        }
      }
    }
  }
}
