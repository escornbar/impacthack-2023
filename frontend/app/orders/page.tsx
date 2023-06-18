"use client";

import { useEffect, useState } from "react";



import { Invoice, Order, columns } from "./columns";
import { DataTable } from "./data-table";


export async function getPO(poId: number) {
  const res = await fetch(
    `http://ec2-18-136-195-218.ap-southeast-1.compute.amazonaws.com:8080/api/purchaseOrders/${poId}`
  )
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error("Failed to fetch data")
  }

  return res.json()
}
export async function getTrackings() {
  const res = await fetch(
    `http://ec2-18-136-195-218.ap-southeast-1.compute.amazonaws.com:8080/api/orderTrackings`
  )
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error("Failed to fetch data")
  }

  return res.json()
}

export async function getInvoice() {
  const res = await fetch(
    "http://ec2-18-136-195-218.ap-southeast-1.compute.amazonaws.com:8080/api/invoices"
  )
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error("Failed to fetch data")
  }
  return res.json()
}

export async function getData(): Promise<Invoice[]> {
  const data: Invoice[] = []
  const trackings = await getTrackings()
  const invoices = await getInvoice()
  invoices.map(async (invoice: any) => {
    const newInvoice: Invoice = {
      id: invoice.id,
      total: invoice.total,
      issuedDate: invoice.issuedDate,
      paymentDeadline: invoice.paymentDeadline,
      supplier: invoice.supplier,
      distributor: invoice.distributor,
      purchaseOrderId: invoice.purchaseOrderId,
      PO: await getPO(invoice.purchaseOrderId),
      downPayment: invoice.downPayment,
      remainingPayment: invoice.remainingPayment,
      tracking: (trackings.find((tracking: any) => tracking.purchaseOrderId === invoice.purchaseOrderId) !== undefined) ? trackings.find((tracking: any) => tracking.purchaseOrderId === invoice.purchaseOrderId).trackingNo : "Not applicable",
    }
    data.push(newInvoice)
  })
  console.log(data)
  return data
  // return [
  //   {
  //     id: 1,
  //     orderdate: new Date('2023-06-17T10:30:00Z'),
  //     customer: "Ah Kok Berhad",
  //     items: [{
  //       id: 1,
  //       name: "Item 1",
  //       unitprice: 100,
  //       quantity: 10,
  //       total: 1000,
  //     },
  //     {
  //       id: 2,
  //       name: "Item 2",
  //       unitprice: 100,
  //       quantity: 10,
  //       total: 1000,
  //     },
  //   ],
  //     total: 1000,
  //     downpayment: 30,
  //     remaining: 700,
  //     status: "Completed",
  //     tracking: "123456789",
  //     bank: "Down Payment Requested",
  //   },
  //   {
  //     id: 1,
  //     orderdate: new Date('2023-06-17T10:30:00Z'),
  //     customer: "Ah Kok Berhad",
  //     items: [],
  //     total: 3000,
  //     downpayment: 30,
  //     remaining: 200,
  //     status: "Completed",
  //     tracking: "123456789",
  //     bank: "Down Payment Requested",
  //   },
  //   {
  //     id: 1,
  //     orderdate: new Date('2023-06-17T10:30:00Z'),
  //     customer: "Ah Kok Berhad",
  //     items: [],
  //     total: 2000,
  //     downpayment: 30,
  //     remaining: 800,
  //     status: "Completed",
  //     tracking: "123456789",
  //     bank: "Down Payment Requested",
  //   },
  //   // ...
  // ]
}

export default async function PurchaseOrder() {
  const data = await getData()

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
        <div className="flex items-center justify-between space-y-2 overflow-x-auto">
          <DataTable columns={columns} data={data} />
        </div>
      </div>
    </>
  )
}