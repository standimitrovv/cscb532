import { IShipment } from './models/IShipment';

type ShipmentStatus = 'Pending' | 'Out for delivery' | 'Delivered';

const shipmentStatusMap: Record<IShipment['shipmentStatus'], ShipmentStatus> = {
  IN_PROCESS: 'Pending',
  SENT: 'Out for delivery',
  IN_TRANSIT: 'Out for delivery',
  COMPLETED: 'Delivered',
};

export const Shipment: React.FunctionComponent<{
  shipment: IShipment;
  isSenderSelf: boolean;
  isReceiverSelf: boolean;
}> = ({ shipment, isSenderSelf, isReceiverSelf }) => {
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
        <div className='grid grid-cols-2 items-center mb-3 gap-16'>
          <div className='flex flex-col'>
            <span className='text-slate-600 text-sm'>Sender:</span>
            <span className='text-md font-semibold'>
              {isSenderSelf ? 'You' : shipment.sender.fullName}
            </span>
          </div>
          <div className='flex flex-col'>
            <span className='text-slate-600 text-sm'>Receiver:</span>
            <span className='text-md font-semibold'>
              {isReceiverSelf ? 'You' : shipment.receiver.fullName}
            </span>
          </div>
        </div>
        <div className='grid grid-cols-2 items-center gap-16'>
          <div className='flex flex-col'>
            <span className='text-slate-600 text-sm'>Destination:</span>
            <span className='text-md font-semibold'>
              {shipment.deliveryAddress}
            </span>
          </div>
          <div className='flex flex-col'>
            <span className='text-slate-600 text-sm'>Created At:</span>
            <span className='text-md font-semibold'>{shipment.createdAt}</span>
          </div>
        </div>
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
