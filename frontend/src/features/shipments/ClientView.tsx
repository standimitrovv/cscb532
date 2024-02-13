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
  Sent: 'IN_PROCESS',
  Expected: 'SENT' || 'IN_TRANSIT',
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

      <div className='grid grid-cols-1 justify-items-center gap-4 md:grid-cols-2 lg:grid-cols-3 lg:justify-items-start 2xl:grid-cols-4'>
        {shipments.map((shipment) => (
          <Shipment
            key={shipment.id}
            isSelf={shipment.sender.id === user?.id}
            shipment={shipment}
          />
        ))}
      </div>
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

type ShipmentStatus = 'Pending' | 'Out for delivery' | 'Delivered';

const shipmentStatusMap: Record<Shipment['shipmentStatus'], ShipmentStatus> = {
  IN_PROCESS: 'Pending',
  SENT: 'Out for delivery',
  IN_TRANSIT: 'Out for delivery',
  COMPLETED: 'Delivered',
};

const Shipment: React.FunctionComponent<{
  shipment: Shipment;
  isSelf: boolean;
}> = ({ shipment, isSelf }) => {
  const shipmentStatus = shipmentStatusMap[shipment.shipmentStatus];

  const shipmentStatusTextStyle =
    shipmentStatus === 'Pending'
      ? 'text-red-600 bg-red-100'
      : shipmentStatus === 'Delivered'
      ? 'text-green-600 bg-green-100'
      : shipmentStatus === 'Out for delivery'
      ? 'text-orange-600 bg-orange-100'
      : '';

  return (
    <div className='w-80 h-fit border rounded-lg'>
      <div className='flex justify-between items-center border-b p-3'>
        <span className='font-bold'>#{shipment.id}</span>
        <span
          className={`border p-2 rounded-lg font-semibold ${shipmentStatusTextStyle}`}
        >
          {shipmentStatus}
        </span>
      </div>
      <div className='flex flex-col border-b p-3'>
        <div className='mb-3 flex flex-col'>
          <span className='text-slate-600 text-sm'>Sender:</span>
          <span className='text-md font-semibold'>
            {isSelf ? 'You' : shipment.sender.fullName}
          </span>
        </div>
        <div className='mb-3 flex flex-col'>
          <span className='text-slate-600 text-sm'>Destination Address:</span>
          <span className='text-md font-semibold'>
            {shipment.deliveryAddress}
          </span>
        </div>
        <span className='text-slate-600 text-sm'>Created At:</span>
        <span className='text-md font-semibold'>{shipment.createdAt}</span>
      </div>
      <div className='p-3 flex flex-col gap-1'>
        <div>
          <span className='text-slate-600 text-sm'>Weight:</span>
          <span className='text-md font-semibold ml-2'>
            {shipment.weight} KG
          </span>
        </div>
        <div>
          <span className='text-slate-600 text-sm'>Shipment Cost:</span>
          <span className='text-md font-semibold ml-2'>
            {shipment.shipmentCost} BGN
          </span>
        </div>
        <div>
          <span className='text-slate-600 text-sm'>Delivery Fee:</span>
          <span className='text-md font-semibold ml-2'>
            {shipment.deliveryFee} BGN
          </span>
        </div>
      </div>
    </div>
  );
};
