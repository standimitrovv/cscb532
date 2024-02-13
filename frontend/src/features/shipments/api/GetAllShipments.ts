import { Shipment } from '../models/Shipment';

interface GetAllShipmentsModel {
  userId: number;
  shipmentStatus: Shipment['shipmentStatus'];
}

export const getAllShipments = async (model: GetAllShipmentsModel) =>
  (
    await fetch(
      `${import.meta.env.VITE_API}/shipments?clientId=${
        model.userId
      }&shipmentStatus=${model.shipmentStatus}`
    )
  ).json();
