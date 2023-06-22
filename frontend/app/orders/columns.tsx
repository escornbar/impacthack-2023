"use client";

import Link from "next/link";
import { ColumnDef } from "@tanstack/react-table";
import { ArrowUpDown, MoreHorizontal } from "lucide-react";



import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";





// export type Item = {
//   id: number
//   name: string
//   unitprice: number
//   quantity: number
//   total: number
// }

// export type Order = {
//   id: number
//   orderdate: Date
//   items: Item[]
//   total: number
//   downpayment: number
//   remaining: number
//   status: "Placed" | "Preparing" | "Shipped" | "Delivered" | "Completed"
//   tracking: string
//   bank: "Down Payment Requested" | "Down Payment Received" | "Full Payment Requested" | "Full Payment Received"
//   customer: string
// }

export type Invoice = {
  id: number
  total: number
  issuedDate: Date
  paymentDeadline: Date
  supplier: string
  distributor: string
  purchaseOrderId: number
  PO: any
  downPayment: number
  remainingPayment: any
  tracking?: any
}

export const columns: ColumnDef<Invoice>[] = [
  {
    accessorKey: "purchaseOrderId",
    header: "Order ID",
    cell: ({ row }) => {
      return <p className="text-right">{row.original.purchaseOrderId}</p>
    },
  },
  {
    accessorKey: "PO.orderDate",
    header: "Date Placed",
    cell: ({ row }) => {
      return <p>{row.original.PO.orderDate}</p>
    },
  },
  { accessorKey: "PO.distributor.title", header: "Customer" },
  {
    accessorKey: "PO.total",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Total Amount
          <ArrowUpDown className="w-4 h-4 ml-2" />
        </Button>
      )
    },
    cell: ({ row }) => {
      const amount = parseFloat(row.original.PO.total)
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "MYR",
      }).format(amount)

      return <div className="font-medium text-center">{formatted}</div>
    },
  },
  {
    accessorKey: "downPayment",
    header: "Down Payment (%)",
    cell: ({ row }) => {
      return <p className="text-center">{row.original.downPayment}</p>
    },
  },
  {
    accessorKey: "remainingPayment",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Remaining Amount
          <ArrowUpDown className="w-4 h-4 ml-2" />
        </Button>
      )
    },
    cell: ({ row }) => {
      const amount = parseFloat(row.original.remainingPayment)
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "MYR",
      }).format(amount)

      return (
        <div className="font-bold text-center text-destructive">
          {formatted}
        </div>
      )
    },
  },
  {
    accessorKey: "PO.orderStatus",
    header: "Order Status",
    cell: ({ row }) => {
      return (
        <Badge
          variant={
            row.original.PO.orderStatus == "Completed" ? "success" : "default"
          }
        >
          {row.original.PO.orderStatus}
        </Badge>
      )
    },
  },
  { accessorKey: "tracking", header: "Tracking Number" },
  // { accessorKey: "bank", header: "Bank Status" },
  {
    id: "actions",
    cell: ({ row }) => {
      return (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="w-8 h-8 p-0">
              <span className="sr-only">Open menu</span>
              <MoreHorizontal className="w-4 h-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuLabel>Actions</DropdownMenuLabel>
            <DropdownMenuSeparator />
            {/* <DropdownMenuItem asChild><Link href={`orders/${row.original.id}`}>View order details</Link></DropdownMenuItem> */}
            <DropdownMenuItem asChild>
              <Link
                href={{
                  pathname: `orders/view`,
                  query: {
                    orderid: `${row.original.PO.id}`,
                  },
                }}
              >
                View order details
              </Link>
            </DropdownMenuItem>
            <DropdownMenuItem>Send invoice</DropdownMenuItem>
            <DropdownMenuItem
              onClick={() =>
                navigator.clipboard.writeText(row.original.tracking)
              }
            >
              Copy tracking number
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      )
    },
  },
]