

type NewListingProps = {
    setCreateListing: (createListing: boolean| "created") => void
    accessToken: string
}

const NewListing = ({setCreateListing, accessToken}: NewListingProps) => {
    const onSubmit = (e:any) => {
        e.preventDefault()
        const form = e.currentTarget
        const formData = new FormData(form)

        fetch("http://localhost:8080/api/items", {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${accessToken}}`
            },
            body: formData
        }).then(res => {
            if (res.ok) {
                setCreateListing("created")
            }
        }).catch(err => {
            console.log(err)
        })
    }
        

    const NewListingForm = () => {
        return (
            <>
            <iframe name="dummyframe" id="dummyframe" style={{display: "none"}}></iframe>
            {/* <form method="POST" action={`http://localhost:8080/api/items`} encType="multipart/form-data" target="dummyframe"> */}
            <form onSubmit={(e) =>{
                e.preventDefault()
                onSubmit(e)
            }}>
                <div className="flex flex-col mb-4">
                    <label className="text-left">Name</label>
                    <input name="name" type="text" className="border border-gray-300 p-2 rounded-lg"/>
                </div>
                <div className="flex flex-col mb-4">
                    <label className="text-left">Description</label>
                    <input name="description"  type="text" className="border border-gray-300 p-2 rounded-lg"/>
                </div>
                <div className="flex flex-col mb-4">
                    <label className="text-left">Price</label>
                    <input name="price"  type="text" className="border border-gray-300 p-2 rounded-lg"/>
                </div>
                {/* <div className="flex flex-col mb-4">
                    <label className="text-left">Tok Token Price</label>
                    <input name="toktoken"  type="text" className="border border-gray-300 p-2 rounded-lg"/>
                </div> */}
                <div className="flex flex-col mb-4">
                    <label className="text-left">Quantity</label>
                    <input name="quantity"  type="text" className="border border-gray-300 p-2 rounded-lg"/>
                </div>
                <div className="flex flex-col mb-4">
                    <label className="text-left">Image</label>
                    <div className="flex mb-4">
                        {/* <img className="mr-4" alt="No Image" style={{height:50, width:50, objectFit: "cover"}}/>        */}
                        <input name="image" type="file" accept="image/*" className="border border-gray-300 p-2 rounded-lg"/>
                    </div>
                </div>
                <div className="flex justify-center">
                    <button className="bg-blue-500 text-white px-4 py-2 rounded-lg">Create</button>
                </div>
            </form>
            </>   
        )
    }


    return (
        <div
        id="edit-listing-popup"
        className="bg-black/50 overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 h-full flex items-center justify-center"
        onClick={(e) => {
            setCreateListing(false)
        }}
        >
            <div className="relative p-4 w-full max-w-md h-full md:h-auto z-1" onClick={(e) =>{
                e.stopPropagation()
            }}>
                <div className="relative bg-white rounded-lg shadow">
                    <div className="text-center p-5">
                        Listing
                        <NewListingForm />
                    </div>
                </div>
            </div>

        </div>
    )
}

export default NewListing