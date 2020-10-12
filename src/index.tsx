import { NativeModules } from 'react-native';

type SamsungAccessoryType = {
  multiply(a: number, b: number): Promise<number>;
};

const { SamsungAccessory } = NativeModules;

export default SamsungAccessory as SamsungAccessoryType;
