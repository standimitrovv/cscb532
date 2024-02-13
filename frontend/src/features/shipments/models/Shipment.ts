export interface Shipment {
  id: number;
  createdAt: string;
  deliveryAddress: string;
  deliveryFee: number;
  deliveryType: 'OFFICE' | 'ADDRESS';
  shipmentCost: number;
  shipmentStatus: 'IN_PROCESS' | 'SENT' | 'IN_TRANSIT' | 'COMPLETED';
  weight: number;
  office: Office;
  receiver: Client;
  sender: Client;
  registeredByEmployee: Employee;
  lastUpdatedByEmployee: Employee;
}

interface Person {
  id: number;
  email: string;
  fullName: string;
  phoneNumber: string;
}

interface Client extends Person {}

interface Employee extends Person {
  employeeType: 'COURIER' | 'OFFICE_STAFF';
}

interface Office {
  id: number;
  address: string;
  phoneNumber: string;
}
