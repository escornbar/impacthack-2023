import { useEffect, useState } from "react";
import { Loader2 } from "lucide-react";



import { Invoice, PO, columns } from "./columns";
import { DataTable } from "./data-table";


export async function getPO() {
  const res = await fetch(
    `http://ec2-13-215-176-102.ap-southeast-1.compute.amazonaws.com:8080/api/purchaseOrders`
  )
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error("Failed to fetch data")
  }

  return res.json()
}
export async function getTrackings() {
  const res = await fetch(
    `http://ec2-13-215-176-102.ap-southeast-1.compute.amazonaws.com:8080/api/orderTrackings`
  )
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error("Failed to fetch data")
  }

  return res.json()
}

export async function getInvoice() {
  const res = await fetch(
    "http://ec2-13-215-176-102.ap-southeast-1.compute.amazonaws.com:8080/api/invoices"
  )
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error("Failed to fetch data")
  }
  return res.json()
}

// export async function getData(): Promise<PO[]> {
//   const data: PO[] = []
//   const POs = await getPO()
//   POs.map(async (PO: PO) => {
//     const newPO: PO = {
//       poId: PO.poId,
//       total: PO.total,
//       orderDate: PO.orderDate,
//       supplier: PO.supplier.
//       distributor: string
//       purchaseOrderStatus: string
//     invoice: Invoice
//     }
//     data.push(newInvoice)
//   })
//   // console.log(data)
//   return data
// }

export default async function PurchaseOrder() {
  const data = await getPO()
  console.log(data)
  return (
    <>
      <div className="flex-col md:flex">
        <div className="flex-1 py-8 pt-6 space-y-4">
          <div className="flex items-center justify-between space-y-2">
            <h2 className="text-3xl font-bold tracking-tight">
              Purchase Orders
            </h2>
          </div>
        </div>
          <DataTable columns={columns} data={data} />
      </div>
    </>
  )
}