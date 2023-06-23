import { DataTable } from "./data-table";

export default function Sales() {
  return (
    <>
      <div className="flex-col md:flex">
        <div className="flex-1 space-y-4 py-8 pt-6">
          <div className="flex items-center justify-between space-y-2">
            <h2 className="text-3xl font-bold tracking-tight">
              Purchase Orders
            </h2>
          </div>
        </div>
        <div className="flex items-center justify-between space-y-2 overflow-x-auto">
          {/* <DataTable columns={columns} data={data} /> */}
        </div>
      </div>
    </>
  )
}
