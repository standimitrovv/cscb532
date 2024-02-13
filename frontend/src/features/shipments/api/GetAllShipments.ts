export const getAllShipments = async () =>
  await fetch(`${import.meta.env.VITE_API}/shipments`);
