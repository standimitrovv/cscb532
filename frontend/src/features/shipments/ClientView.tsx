import { useEffect, useState } from 'react';
import { useAuth } from '../auth/hooks/UseAuth';
import { Shipment } from './Shipment';
import { Tab } from './Tab';
import { getAllShipments } from './api/GetAllShipments';
import { IShipment } from './models/IShipment';

type Tab = 'Sent' | 'Expected' | 'Received';

interface Tabs {
  id: number;
  name: Tab;
}

const tabs: Tabs[] = [
  {
    id: 1,
    name: 'Sent',
  },
  {
    id: 2,
    name: 'Expected',
  },
  {
    id: 3,
    name: 'Received',
  },
];

export const ClientView = () => {
  const { user } = useAuth();

  const [activeTab, setActiveTab] = useState<Tab>('Sent');

  const [shipments, setShipments] = useState<IShipment[]>([]);

  useEffect(() => {
    (async () => {
      const res = await getAllShipments();

      if (res.ok) {
        setShipments(await res.json());
      }
    })();
  }, []);

  if (!user?.person?.id) {
    return null;
  }

  const { sentShipments, expectedShipments, receivedShipments } =
    distributeShipmentsByType(shipments, user.person.id);

  const shipmentsToMap =
    activeTab === 'Sent'
      ? sentShipments
      : activeTab === 'Expected'
      ? expectedShipments
      : activeTab === 'Received'
      ? receivedShipments
      : [];

  return (
    <div>
      <div className='text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:text-gray-400 dark:border-gray-700 mb-6 w-fit'>
        <ul className='flex flex-wrap -mb-px'>
          {tabs.map((t) => (
            <Tab
              key={t.id}
              text={t.name}
              isActive={t.name === activeTab}
              onClick={() => setActiveTab(t.name)}
            />
          ))}
        </ul>
      </div>

      <div className='grid grid-cols-1 justify-items-center gap-4 md:grid-cols-2 lg:grid-cols-3 lg:justify-items-start 2xl:grid-cols-4'>
        {shipmentsToMap.map((shipment) => (
          <Shipment
            key={shipment.id}
            isSenderSelf={shipment.sender.id === user?.person?.id}
            isReceiverSelf={shipment.receiver.id === user?.person?.id}
            shipment={shipment}
          />
        ))}
      </div>
    </div>
  );
};

const distributeShipmentsByType = (shipments: IShipment[], userId: number) => {
  const sentShipments = shipments.filter((sh) => sh.sender.id === userId);

  const expectedShipments = shipments.filter(
    (sh) => sh.receiver.id === userId && sh.shipmentStatus !== 'COMPLETED'
  );

  const receivedShipments = shipments.filter(
    (sh) => sh.receiver.id === userId && sh.shipmentStatus === 'COMPLETED'
  );

  return {
    sentShipments,
    expectedShipments,
    receivedShipments,
  };
};
