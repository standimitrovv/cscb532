import { useEffect, useState } from 'react';
import { useAuth } from '../auth/hooks/UseAuth';
import { getAllShipments } from './api/GetAllShipments';
import { Shipment } from './models/Shipment';

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

const activeTabToShipmentStatusMap: Record<Tab, Shipment['shipmentStatus']> = {
  Sent: 'IN_PROCESS' || 'SENT',
  Expected: 'IN_TRANSIT',
  Received: 'COMPLETED',
};

export const ClientView = () => {
  const { user } = useAuth();

  const [activeTab, setActiveTab] = useState<Tab>('Sent');

  const [shipments, setShipments] = useState<Shipment[]>([]);

  useEffect(() => {
    (async () => {
      if (!user) {
        return;
      }

      const shipments = await getAllShipments({
        userId: user?.id,
        shipmentStatus: activeTabToShipmentStatusMap[activeTab],
      });

      setShipments(shipments);
    })();
  }, [activeTab, user]);

  return (
    <div>
      <div className='text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:text-gray-400 dark:border-gray-700 mb-6'>
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

      {shipments.map((shipment) => (
        <Shipment key={shipment.id} shipment={shipment} />
      ))}
    </div>
  );
};

const Tab: React.FunctionComponent<{
  text: string;
  isActive: boolean;
  onClick: () => void;
}> = ({ text, isActive, onClick }) => {
  return (
    <li>
      <a
        className={`inline-block p-4 border-b-2 rounded-t-lg ${
          isActive
            ? ' text-blue-600  border-blue-600 active dark:text-blue-500 dark:border-blue-500'
            : 'border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300 cursor-pointer'
        } `}
        onClick={onClick}
      >
        {text}
      </a>
    </li>
  );
};

const Shipment: React.FunctionComponent<{ shipment: Shipment }> = ({
  shipment,
}) => {
  return <div>{shipment.shipmentStatus}</div>;
};
