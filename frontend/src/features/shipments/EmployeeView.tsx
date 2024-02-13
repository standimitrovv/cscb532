import { useEffect, useState } from 'react';
import { Shipment } from './Shipment';
import { Tab } from './Tab';
import { getAllShipments } from './api/GetAllShipments';
import { IShipment } from './models/IShipment';

type Tab = 'All';

interface Tabs {
  id: number;
  name: Tab;
}

const tabs: Tabs[] = [
  {
    id: 1,
    name: 'All',
  },
];

export const EmployeeView = () => {
  const [shipments, setShipments] = useState<IShipment[]>([]);

  useEffect(() => {
    (async () => {
      const res = await getAllShipments();

      if (res.ok) {
        setShipments(await res.json());
      }
    })();
  }, []);

  return (
    <div>
      <div className='text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:text-gray-400 dark:border-gray-700 mb-6 w-fit'>
        <ul className='flex flex-wrap -mb-px'>
          {tabs.map((t) => (
            <Tab
              key={t.id}
              text={t.name}
              isActive={t.name === 'All'}
              onClick={() => {}}
            />
          ))}
        </ul>
      </div>

      <div className='grid grid-cols-1 justify-items-center gap-4 md:grid-cols-2 lg:grid-cols-3 lg:justify-items-start 2xl:grid-cols-4'>
        {shipments.map((shipment) => (
          <Shipment
            key={shipment.id}
            shipment={shipment}
            isSenderSelf={false}
            isReceiverSelf={false}
          />
        ))}
      </div>
    </div>
  );
};
