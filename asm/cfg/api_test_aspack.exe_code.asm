0x00404001:	pusha
0x00404002:	call 0x0040400a
0x0040400a:	popl %ebp
0x0040400b:	incl %ebp
0x0040400c:	pushl %ebp
0x0040400d:	ret

0x00404008:	jmp 0x0040400e
0x0040400e:	call 0x00404014
0x00404014:	popl %ebp
0x00404015:	movl %ebx, $0xffffffed<UINT32>
0x0040401a:	addl %ebx, %ebp
0x0040401c:	subl %ebx, $0x4000<UINT32>
0x00404022:	cmpl 0x488(%ebp), $0x0<UINT8>
0x00404029:	movl 0x488(%ebp), %ebx
0x0040402f:	jne 971
0x00404035:	leal %eax, 0x494(%ebp)
0x0040403b:	pushl %eax
0x0040403c:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x00404042:	movl 0x48c(%ebp), %eax
0x00404048:	movl %esi, %eax
0x0040404a:	leal %edi, 0x51(%ebp)
0x0040404d:	pushl %edi
0x0040404e:	pushl %esi
0x0040404f:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00404055:	stosl %es:(%edi), %eax
0x00404056:	movb %al, $0x0<UINT8>
0x00404058:	scasb %al, %es:(%edi)
0x00404059:	jne 0x00404058
0x0040405b:	cmpb (%edi), %al
0x0040405d:	jne 0x0040404d
0x0040405f:	leal %eax, 0x7a(%ebp)
0x00404062:	jmp 0x0040408d
0x0040408d:	movl %ebx, 0x595(%ebp)
0x00404093:	orl %ebx, %ebx
0x00404095:	je 0x004040a1
0x004040a1:	leal %esi, 0x5c5(%ebp)
0x004040a7:	cmpl (%esi), $0x0<UINT8>
0x004040aa:	je 266
0x004040b0:	pushl $0x4<UINT8>
0x004040b2:	pushl $0x1000<UINT32>
0x004040b7:	pushl $0x1800<UINT32>
0x004040bc:	pushl $0x0<UINT8>
0x004040be:	call VirtualAlloc@kernel32.dll
VirtualAlloc@kernel32.dll: API Node	
0x004040c1:	movl 0x148(%ebp), %eax
0x004040c7:	movl %eax, 0x4(%esi)
0x004040ca:	addl %eax, $0x10e<UINT32>
0x004040cf:	je 183
0x004040d5:	pushl $0x4<UINT8>
0x004040d7:	pushl $0x1000<UINT32>
0x004040dc:	pushl %eax
0x004040dd:	pushl $0x0<UINT8>
0x004040df:	call VirtualAlloc@kernel32.dll
0x004040e2:	movl 0x144(%ebp), %eax
0x004040e8:	pushl %esi
0x004040e9:	movl %ebx, (%esi)
0x004040eb:	addl %ebx, 0x488(%ebp)
0x004040f1:	pushl 0x148(%ebp)
0x004040f7:	pushl 0x4(%esi)
0x004040fa:	pushl %eax
0x004040fb:	pushl %ebx
0x004040fc:	call 0x004046c8
0x004046c8:	movl %eax, 0x10(%esp)
0x004046cc:	subl %esp, $0x354<UINT32>
0x004046d2:	leal %ecx, 0x4(%esp)
0x004046d6:	pushl %eax
0x004046d7:	call 0x00404a84
0x00404a84:	pushl %ebx
0x00404a85:	pushl %esi
0x00404a86:	pushl %edi
0x00404a87:	movl %edi, %ecx
0x00404a89:	xorl %edx, %edx
0x00404a8b:	xorl %eax, %eax
0x00404a8d:	leal %esi, 0x268(%edi)
0x00404a93:	movl (%esi), %edx
0x00404a95:	pushl %esi
0x00404a96:	call 0x00404cf2
0x00404cf2:	call 0x00404cf8
0x00404cf8:	popl %esi
0x00404cf9:	subl %esi, $0x46ca5f<UINT32>
0x00404cff:	ret

0x00404a9b:	movb %cl, 0x46c4d2(%eax,%esi)
0x00404aa2:	popl %esi
0x00404aa3:	movl %ebx, $0x1<UINT32>
0x00404aa8:	addl %esi, $0x4<UINT8>
0x00404aab:	shll %ebx, %cl
0x00404aad:	addl %edx, %ebx
0x00404aaf:	incl %eax
0x00404ab0:	cmpl %eax, $0x3a<UINT8>
0x00404ab3:	jb 0x00404a93
0x00404ab5:	movl %eax, 0x10(%esp)
0x00404ab9:	leal %ecx, 0x10(%edi)
0x00404abc:	pushl %eax
0x00404abd:	pushl $0x2d1<UINT32>
0x00404ac2:	call 0x0040480f
0x0040480f:	movl %eax, 0x4(%esp)
0x00404813:	movl %edx, 0x8(%esp)
0x00404817:	movl 0x84(%ecx), %eax
0x0040481d:	movl 0x88(%ecx), %edx
0x00404823:	leal %eax, (%edx,%eax,4)
0x00404826:	movl 0x8c(%ecx), %eax
0x0040482c:	addl %eax, $0x100<UINT32>
0x00404831:	ret $0x8<UINT16>

0x00404ac7:	pushl %eax
0x00404ac8:	pushl $0x1c<UINT8>
0x00404aca:	leal %ecx, 0xa0(%edi)
0x00404ad0:	call 0x0040480f
0x00404ad5:	pushl %eax
0x00404ad6:	pushl $0x8<UINT8>
0x00404ad8:	leal %ecx, 0x130(%edi)
0x00404ade:	call 0x0040480f
0x00404ae3:	pushl %eax
0x00404ae4:	pushl $0x13<UINT8>
0x00404ae6:	leal %ecx, 0x1c0(%edi)
0x00404aec:	call 0x0040480f
0x00404af1:	movl 0x260(%edi), %eax
0x00404af7:	popl %edi
0x00404af8:	popl %esi
0x00404af9:	addl %eax, $0x2f5<UINT32>
0x00404afe:	popl %ebx
0x00404aff:	ret $0x4<UINT16>

0x004046dc:	movl %ecx, 0x35c(%esp)
0x004046e3:	movl %edx, 0x358(%esp)
0x004046ea:	pushl %ecx
0x004046eb:	pushl %edx
0x004046ec:	leal %ecx, 0xc(%esp)
0x004046f0:	call 0x00404b02
0x00404b02:	movl %eax, 0x8(%esp)
0x00404b06:	movl %edx, %ecx
0x00404b08:	movl %ecx, 0x4(%esp)
0x00404b0c:	pushl %edi
0x00404b0d:	movl (%edx), %eax
0x00404b0f:	leal %eax, 0x4(%edx)
0x00404b12:	movl (%eax), %ecx
0x00404b14:	movl 0x4(%eax), $0x20<UINT32>
0x00404b1b:	movl 0x10(%edx), %eax
0x00404b1e:	movl 0xa0(%edx), %eax
0x00404b24:	movl 0x130(%edx), %eax
0x00404b2a:	movl 0x1c0(%edx), %eax
0x00404b30:	xorl %eax, %eax
0x00404b32:	movl %ecx, $0xbd<UINT32>
0x00404b37:	movl 0x250(%edx), %eax
0x00404b3d:	movl 0x254(%edx), %eax
0x00404b43:	movl 0x258(%edx), %eax
0x00404b49:	movl %edi, 0x260(%edx)
0x00404b4f:	movl 0x25c(%edx), %eax
0x00404b55:	rep stosl %es:(%edi), %eax
0x00404b57:	movl %ecx, %edx
0x00404b59:	stosb %es:(%edi), %al
0x00404b5a:	call 0x00404b63
0x00404b63:	subl %esp, $0x30c<UINT32>
0x00404b69:	pushl %ebx
0x00404b6a:	movl %ebx, %ecx
0x00404b6c:	pushl %ebp
0x00404b6d:	pushl %esi
0x00404b6e:	leal %ebp, 0x4(%ebx)
0x00404b71:	pushl %edi
0x00404b72:	pushl $0x1<UINT8>
0x00404b74:	movl %ecx, %ebp
0x00404b76:	call 0x004047a4
0x004047a4:	pushl %ecx
0x004047a5:	movl %edx, %ecx
0x004047a7:	pushl %esi
0x004047a8:	movl %ecx, $0x8<UINT32>
0x004047ad:	pushl %edi
0x004047ae:	cmpl 0x4(%edx), %ecx
0x004047b1:	jb 0x004047e8
0x004047b3:	pushl %ebx
0x004047b4:	movl %esi, $0xfffffff8<UINT32>
0x004047b9:	movl %eax, (%edx)
0x004047bb:	movb %bl, (%eax)
0x004047bd:	incl %eax
0x004047be:	movb 0xc(%esp), %bl
0x004047c2:	movl (%edx), %eax
0x004047c4:	movl %eax, 0x8(%edx)
0x004047c7:	movl %edi, 0xc(%esp)
0x004047cb:	shll %eax, $0x8<UINT8>
0x004047ce:	andl %edi, $0xff<UINT32>
0x004047d4:	orl %eax, %edi
0x004047d6:	movl %edi, 0x4(%edx)
0x004047d9:	addl %edi, %esi
0x004047db:	movl 0x8(%edx), %eax
0x004047de:	movl %eax, %edi
0x004047e0:	movl 0x4(%edx), %edi
0x004047e3:	cmpl %eax, %ecx
0x004047e5:	jae 0x004047b9
0x004047e7:	popl %ebx
0x004047e8:	movl %esi, 0x4(%edx)
0x004047eb:	movl %eax, 0x8(%edx)
0x004047ee:	movl %edi, 0x10(%esp)
0x004047f2:	subl %ecx, %esi
0x004047f4:	shrl %eax, %cl
0x004047f6:	movl %ecx, $0x18<UINT32>
0x004047fb:	subl %ecx, %edi
0x004047fd:	andl %eax, $0xffffff<UINT32>
0x00404802:	shrl %eax, %cl
0x00404804:	addl %esi, %edi
0x00404806:	popl %edi
0x00404807:	movl 0x4(%edx), %esi
0x0040480a:	popl %esi
0x0040480b:	popl %ecx
0x0040480c:	ret $0x4<UINT16>

0x00404b7b:	testl %eax, %eax
0x00404b7d:	jne 14
0x00404b7f:	movl %edi, 0x260(%ebx)
0x00404b85:	movl %ecx, $0xbd<UINT32>
0x00404b8a:	rep stosl %es:(%edi), %eax
0x00404b8c:	stosb %es:(%edi), %al
0x00404b8d:	xorl %esi, %esi
0x00404b8f:	pushl $0x4<UINT8>
0x00404b91:	movl %ecx, %ebp
0x00404b93:	call 0x004047a4
0x00404b98:	movb 0x10(%esp,%esi), %al
0x00404b9c:	incl %esi
0x00404b9d:	cmpl %esi, $0x13<UINT8>
0x00404ba0:	jb 0x00404b8f
0x00404ba2:	leal %edi, 0x1c0(%ebx)
0x00404ba8:	leal %eax, 0x10(%esp)
0x00404bac:	pushl %eax
0x00404bad:	movl %ecx, %edi
0x00404baf:	call 0x00404834
0x00404834:	subl %esp, $0x98<UINT32>
0x0040483a:	pushl %ebx
0x0040483b:	pushl %ebp
0x0040483c:	pushl %esi
0x0040483d:	movl %edx, %ecx
0x0040483f:	pushl %edi
0x00404840:	movl %ecx, $0xf<UINT32>
0x00404845:	movl %ebp, 0x84(%edx)
0x0040484b:	xorl %eax, %eax
0x0040484d:	leal %edi, 0x2c(%esp)
0x00404851:	xorl %esi, %esi
0x00404853:	rep stosl %es:(%edi), %eax
0x00404855:	movl %edi, 0xac(%esp)
0x0040485c:	cmpl %ebp, %esi
0x0040485e:	movl 0x20(%esp), %edx
0x00404862:	jbe 21
0x00404864:	xorl %ecx, %ecx
0x00404866:	movb %cl, (%eax,%edi)
0x00404869:	movl %ebx, 0x28(%esp,%ecx,4)
0x0040486d:	leal %ecx, 0x28(%esp,%ecx,4)
0x00404871:	incl %ebx
0x00404872:	incl %eax
0x00404873:	cmpl %eax, %ebp
0x00404875:	movl (%ecx), %ebx
0x00404877:	jb 0x00404864
0x00404879:	movl %ecx, $0x17<UINT32>
0x0040487e:	movl 0x28(%esp), %esi
0x00404882:	movl 0x4(%edx), %esi
0x00404885:	movl 0x44(%edx), %esi
0x00404888:	movl 0x68(%esp), %esi
0x0040488c:	xorl %edi, %edi
0x0040488e:	movl 0x1c(%esp), %esi
0x00404892:	movl 0x10(%esp), $0x1<UINT32>
0x0040489a:	movl 0x18(%esp), %ecx
0x0040489e:	leal %ebp, 0x8(%edx)
0x004048a1:	movl 0x14(%esp), %esi
0x004048a5:	movl %eax, 0x2c(%esp,%esi)
0x004048a9:	shll %eax, %cl
0x004048ab:	addl %edi, %eax
0x004048ad:	cmpl %edi, $0x1000000<UINT32>
0x004048b3:	movl 0x24(%esp), %edi
0x004048b7:	ja 142
0x004048bd:	movl %eax, 0x28(%esp,%esi)
0x004048c1:	movl (%ebp), %edi
0x004048c4:	movl %ebx, 0x3c(%ebp)
0x004048c7:	addl %eax, %ebx
0x004048c9:	cmpl %ecx, $0x10<UINT8>
0x004048cc:	movl 0x40(%ebp), %eax
0x004048cf:	movl 0x6c(%esp,%esi), %eax
0x004048d3:	jl 0x00404922
0x004048d5:	movl %esi, (%ebp)
0x004048d8:	movl %eax, 0x10(%esp)
0x004048dc:	movl %ebx, 0x1c(%esp)
0x004048e0:	movl %edi, 0x8c(%edx)
0x004048e6:	shrl %esi, $0x10<UINT8>
0x004048e9:	movl %ecx, %esi
0x004048eb:	andl %eax, $0xff<UINT32>
0x004048f0:	subl %ecx, %ebx
0x004048f2:	addl %edi, %ebx
0x004048f4:	movb %bl, %al
0x004048f6:	movl %edx, %ecx
0x004048f8:	movb %bh, %bl
0x004048fa:	movl 0x1c(%esp), %esi
0x004048fe:	movl %eax, %ebx
0x00404900:	movl %esi, 0x14(%esp)
0x00404904:	shll %eax, $0x10<UINT8>
0x00404907:	movw %ax, %bx
0x0040490a:	shrl %ecx, $0x2<UINT8>
0x0040490d:	rep stosl %es:(%edi), %eax
0x0040490f:	movl %ecx, %edx
0x00404911:	movl %edx, 0x20(%esp)
0x00404915:	andl %ecx, $0x3<UINT8>
0x00404918:	rep stosb %es:(%edi), %al
0x0040491a:	movl %edi, 0x24(%esp)
0x0040491e:	movl %ecx, 0x18(%esp)
0x00404922:	movl %eax, 0x10(%esp)
0x00404926:	addl %esi, $0x4<UINT8>
0x00404929:	incl %eax
0x0040492a:	decl %ecx
0x0040492b:	addl %ebp, $0x4<UINT8>
0x0040492e:	cmpl %ecx, $0x9<UINT8>
0x00404931:	movl 0x10(%esp), %eax
0x00404935:	movl 0x18(%esp), %ecx
0x00404939:	movl 0x14(%esp), %esi
0x0040493d:	jge 0x004048a5
0x00404943:	cmpl %edi, $0x1000000<UINT32>
0x00404949:	je 0x0040495a
0x0040495a:	movl %eax, 0x84(%edx)
0x00404960:	xorl %ecx, %ecx
0x00404962:	testl %eax, %eax
0x00404964:	jbe 59
0x00404966:	movl %esi, 0xac(%esp)
0x0040496d:	movb %al, (%ecx,%esi)
0x00404970:	testb %al, %al
0x00404972:	je 0x00404996
0x00404974:	movl %edi, 0x88(%edx)
0x0040497a:	andl %eax, $0xff<UINT32>
0x0040497f:	movl %eax, 0x68(%esp,%eax,4)
0x00404983:	movl (%edi,%eax,4), %ecx
0x00404986:	xorl %eax, %eax
0x00404988:	movb %al, (%ecx,%esi)
0x0040498b:	movl %edi, 0x68(%esp,%eax,4)
0x0040498f:	leal %eax, 0x68(%esp,%eax,4)
0x00404993:	incl %edi
0x00404994:	movl (%eax), %edi
0x00404996:	movl %eax, 0x84(%edx)
0x0040499c:	incl %ecx
0x0040499d:	cmpl %ecx, %eax
0x0040499f:	jb 0x0040496d
0x004049a1:	popl %edi
0x004049a2:	popl %esi
0x004049a3:	popl %ebp
0x004049a4:	movb %al, $0x1<UINT8>
0x004049a6:	popl %ebx
0x004049a7:	addl %esp, $0x98<UINT32>
0x004049ad:	ret $0x4<UINT16>

0x00404bb4:	testb %al, %al
0x00404bb6:	jne 0x00404bc3
0x00404bc3:	xorl %esi, %esi
0x00404bc5:	movl %ecx, %edi
0x00404bc7:	call 0x004049b0
0x004049b0:	pushl %ecx
0x004049b1:	pushl %ebx
0x004049b2:	pushl %esi
0x004049b3:	movl %esi, %ecx
0x004049b5:	pushl %edi
0x004049b6:	movl %eax, (%esi)
0x004049b8:	cmpl 0x4(%eax), $0x8<UINT8>
0x004049bc:	jb 0x004049ee
0x004049ee:	movl %edx, 0x4(%eax)
0x004049f1:	movl %eax, 0x8(%eax)
0x004049f4:	movl %ecx, $0x8<UINT32>
0x004049f9:	subl %ecx, %edx
0x004049fb:	shrl %eax, %cl
0x004049fd:	movl %ecx, 0x24(%esi)
0x00404a00:	andl %eax, $0xfffe00<UINT32>
0x00404a05:	cmpl %eax, %ecx
0x00404a07:	jae 20
0x00404a09:	movl %edx, 0x8c(%esi)
0x00404a0f:	movl %ecx, %eax
0x00404a11:	shrl %ecx, $0x10<UINT8>
0x00404a14:	xorl %ebx, %ebx
0x00404a16:	movb %bl, (%ecx,%edx)
0x00404a19:	movl %edx, %ebx
0x00404a1b:	jmp 0x00404a58
0x00404a58:	movl %ecx, (%esi)
0x00404a5a:	movl %edi, 0x4(%ecx)
0x00404a5d:	addl %edi, %edx
0x00404a5f:	movl 0x4(%ecx), %edi
0x00404a62:	movl %ebx, (%esi,%edx,4)
0x00404a65:	movl %ecx, $0x18<UINT32>
0x00404a6a:	subl %eax, %ebx
0x00404a6c:	subl %ecx, %edx
0x00404a6e:	popl %edi
0x00404a6f:	shrl %eax, %cl
0x00404a71:	movl %ecx, 0x44(%esi,%edx,4)
0x00404a75:	addl %eax, %ecx
0x00404a77:	movl %ecx, 0x88(%esi)
0x00404a7d:	popl %esi
0x00404a7e:	popl %ebx
0x00404a7f:	movl %eax, (%ecx,%eax,4)
0x00404a82:	popl %ecx
0x00404a83:	ret

0x00404bcc:	cmpl %eax, $0x10<UINT8>
0x00404bcf:	jae 0x00404be6
0x00404bd1:	movl %ecx, 0x260(%ebx)
0x00404bd7:	movb %dl, (%ecx,%esi)
0x00404bda:	addb %dl, %al
0x00404bdc:	andb %dl, $0xf<UINT8>
0x00404bdf:	movb 0x24(%esp,%esi), %dl
0x00404be3:	incl %esi
0x00404be4:	jmp 0x00404c46
0x00404c46:	cmpl %esi, $0x2f5<UINT32>
0x00404c4c:	jl 0x00404bc5
0x004049be:	movl %ecx, (%eax)
0x004049c0:	movb %dl, (%ecx)
0x004049c2:	incl %ecx
0x004049c3:	movb 0xc(%esp), %dl
0x004049c7:	movl (%eax), %ecx
0x004049c9:	movl %ecx, 0x8(%eax)
0x004049cc:	movl %edx, 0xc(%esp)
0x004049d0:	shll %ecx, $0x8<UINT8>
0x004049d3:	andl %edx, $0xff<UINT32>
0x004049d9:	orl %ecx, %edx
0x004049db:	movl %edx, 0x4(%eax)
0x004049de:	addl %edx, $0xfffffff8<UINT8>
0x004049e1:	movl 0x8(%eax), %ecx
0x004049e4:	movl %ecx, %edx
0x004049e6:	movl 0x4(%eax), %edx
0x004049e9:	cmpl %ecx, $0x8<UINT8>
0x004049ec:	jae -48
0x00404be6:	jne 0x00404c10
0x00404be8:	pushl $0x2<UINT8>
0x00404bea:	movl %ecx, %ebp
0x00404bec:	call 0x004047a4
0x00404bf1:	addl %eax, $0x3<UINT8>
0x00404bf4:	testl %eax, %eax
0x00404bf6:	jle 78
0x00404bf8:	cmpl %esi, $0x2f5<UINT32>
0x00404bfe:	jnl 82
0x00404c00:	movb %cl, 0x23(%esp,%esi)
0x00404c04:	decl %eax
0x00404c05:	movb 0x24(%esp,%esi), %cl
0x00404c09:	incl %esi
0x00404c0a:	testl %eax, %eax
0x00404c0c:	jg 0x00404bf8
0x00404c0e:	jmp 0x00404c46
0x00404c10:	cmpl %eax, $0x11<UINT8>
0x00404c13:	jne 0x00404c23
0x00404c15:	pushl $0x3<UINT8>
0x00404c17:	movl %ecx, %ebp
0x00404c19:	call 0x004047a4
0x00404c1e:	addl %eax, $0x3<UINT8>
0x00404c21:	jmp 0x00404c2f
0x00404c2f:	testl %eax, %eax
0x00404c31:	jle 19
0x00404c33:	cmpl %esi, $0x2f5<UINT32>
0x00404c39:	jnl 23
0x00404c3b:	movb 0x24(%esp,%esi), $0x0<UINT8>
0x00404c40:	incl %esi
0x00404c41:	decl %eax
0x00404c42:	testl %eax, %eax
0x00404c44:	jg 0x00404c33
0x00404c23:	pushl $0x7<UINT8>
0x00404c25:	movl %ecx, %ebp
0x00404c27:	call 0x004047a4
0x00404c2c:	addl %eax, $0xb<UINT8>
0x00404c52:	leal %edx, 0x24(%esp)
0x00404c56:	leal %ecx, 0x10(%ebx)
0x00404c59:	pushl %edx
0x00404c5a:	call 0x00404834
0x00404c5f:	testb %al, %al
0x00404c61:	jne 0x00404c6e
0x00404c6e:	leal %eax, 0x2f5(%esp)
0x00404c75:	leal %ecx, 0xa0(%ebx)
0x00404c7b:	pushl %eax
0x00404c7c:	call 0x00404834
0x00404c81:	testb %al, %al
0x00404c83:	jne 0x00404c90
0x00404c90:	leal %ecx, 0x311(%esp)
0x00404c97:	pushl %ecx
0x00404c98:	leal %ecx, 0x130(%ebx)
0x00404c9e:	call 0x00404834
0x00404ca3:	testb %al, %al
0x00404ca5:	jne 0x00404cb2
0x00404cb2:	movb 0x264(%ebx), $0x0<UINT8>
0x00404cb9:	xorl %eax, %eax
0x00404cbb:	cmpb 0x311(%esp,%eax), $0x3<UINT8>
0x00404cc3:	jne 0x00404ccd
0x00404ccd:	movb 0x264(%ebx), $0x1<UINT8>
0x00404cd4:	movl %edi, 0x260(%ebx)
0x00404cda:	leal %esi, 0x24(%esp)
0x00404cde:	movl %ecx, $0x2f5<UINT32>
0x00404ce3:	rep movsb %es:(%edi), %ds:(%esi)
0x00404ce5:	popl %edi
0x00404ce6:	popl %esi
0x00404ce7:	popl %ebp
0x00404ce8:	movb %al, $0x1<UINT8>
0x00404cea:	popl %ebx
0x00404ceb:	addl %esp, $0x30c<UINT32>
0x00404cf1:	ret

0x00404b5f:	popl %edi
0x00404b60:	ret $0x8<UINT16>

0x004046f5:	testb %al, %al
0x004046f7:	jne 0x00404703
0x00404703:	movl %ecx, 0x360(%esp)
0x0040470a:	leal %eax, (%esp)
0x0040470d:	pushl %eax
0x0040470e:	pushl %ecx
0x0040470f:	leal %ecx, 0xc(%esp)
0x00404713:	call 0x00404d00
0x00404d00:	subl %esp, $0x14<UINT8>
0x00404d03:	movl %eax, 0x1c(%esp)
0x00404d07:	pushl %ebx
0x00404d08:	pushl %ebp
0x00404d09:	pushl %esi
0x00404d0a:	movl (%eax), $0x0<UINT32>
0x00404d10:	movl %eax, 0x24(%esp)
0x00404d14:	pushl %edi
0x00404d15:	xorl %edi, %edi
0x00404d17:	testl %eax, %eax
0x00404d19:	movl %esi, %ecx
0x00404d1b:	movl 0x10(%esp), %edi
0x00404d1f:	jbe 603
0x00404d25:	leal %ecx, 0x10(%esi)
0x00404d28:	call 0x004049b0
0x00404d2d:	cmpl %eax, $0x100<UINT32>
0x00404d32:	jae 0x00404d47
0x00404d34:	movl %ecx, (%esi)
0x00404d36:	movb (%ecx), %al
0x00404d38:	movl %ecx, (%esi)
0x00404d3a:	incl %ecx
0x00404d3b:	incl %edi
0x00404d3c:	movl (%esi), %ecx
0x00404d3e:	movl 0x10(%esp), %edi
0x00404d42:	jmp 0x00404f70
0x00404f70:	cmpl %edi, 0x28(%esp)
0x00404f74:	jb 0x00404d25
0x00404d47:	cmpl %eax, $0x2d0<UINT32>
0x00404d4c:	jae 531
0x00404d52:	addl %eax, $0xffffff00<UINT32>
0x00404d57:	movl %ebp, %eax
0x00404d59:	andl %eax, $0x7<UINT8>
0x00404d5c:	shrl %ebp, $0x3<UINT8>
0x00404d5f:	leal %edx, 0x2(%eax)
0x00404d62:	cmpl %eax, $0x7<UINT8>
0x00404d65:	movl 0x14(%esp), %edx
0x00404d69:	jne 0x00404e03
0x00404e03:	movb %al, 0x264(%esi)
0x00404e09:	movl %ebx, 0x268(%esi,%ebp,4)
0x00404e10:	xorl %edx, %edx
0x00404e12:	pushl %esi
0x00404e13:	call 0x00404cf2
0x00404e18:	movb %dl, 0x46c4d2(%ebp,%esi)
0x00404e1f:	popl %esi
0x00404e20:	testb %al, %al
0x00404e22:	movl %edi, %edx
0x00404e24:	je 118
0x00404e26:	cmpl %edi, $0x3<UINT8>
0x00404e29:	jb 0x00404e9c
0x00404e9c:	cmpl 0x8(%esi), $0x8<UINT8>
0x00404ea0:	jb 0x00404ed3
0x00404ea2:	movl %eax, 0x4(%esi)
0x00404ea5:	movl %edx, 0xc(%esi)
0x00404ea8:	shll %edx, $0x8<UINT8>
0x00404eab:	movb %cl, (%eax)
0x00404ead:	incl %eax
0x00404eae:	movb 0x20(%esp), %cl
0x00404eb2:	movl %ecx, 0x8(%esi)
0x00404eb5:	movl 0x4(%esi), %eax
0x00404eb8:	movl %eax, 0x20(%esp)
0x00404ebc:	andl %eax, $0xff<UINT32>
0x00404ec1:	addl %ecx, $0xfffffff8<UINT8>
0x00404ec4:	orl %edx, %eax
0x00404ec6:	movl %eax, %ecx
0x00404ec8:	cmpl %eax, $0x8<UINT8>
0x00404ecb:	movl 0xc(%esi), %edx
0x00404ece:	movl 0x8(%esi), %ecx
0x00404ed1:	jae -49
0x00404ed3:	movl %edx, 0x8(%esi)
0x00404ed6:	movl %eax, 0xc(%esi)
0x00404ed9:	movl %ecx, $0x8<UINT32>
0x00404ede:	subl %ecx, %edx
0x00404ee0:	addl %edx, %edi
0x00404ee2:	shrl %eax, %cl
0x00404ee4:	movl %ecx, $0x18<UINT32>
0x00404ee9:	movl 0x8(%esi), %edx
0x00404eec:	subl %ecx, %edi
0x00404eee:	andl %eax, $0xffffff<UINT32>
0x00404ef3:	shrl %eax, %cl
0x00404ef5:	addl %ebx, %eax
0x00404ef7:	cmpl %ebx, $0x3<UINT8>
0x00404efa:	jae 0x00404f16
0x00404f16:	movl %eax, 0x254(%esi)
0x00404f1c:	movl %edx, 0x250(%esi)
0x00404f22:	leal %ecx, -3(%ebx)
0x00404f25:	movl 0x258(%esi), %eax
0x00404f2b:	movl 0x254(%esi), %edx
0x00404f31:	movl 0x250(%esi), %ecx
0x00404f37:	movl %eax, (%esi)
0x00404f39:	movl %edi, 0x14(%esp)
0x00404f3d:	incl %ecx
0x00404f3e:	leal %edx, (%eax,%edi)
0x00404f41:	cmpl %eax, %edx
0x00404f43:	movl (%esi), %edx
0x00404f45:	jae 16
0x00404f47:	movl %edx, %eax
0x00404f49:	subl %edx, %ecx
0x00404f4b:	incl %eax
0x00404f4c:	movb %dl, (%edx)
0x00404f4e:	movb -1(%eax), %dl
0x00404f51:	movl %edx, (%esi)
0x00404f53:	cmpl %eax, %edx
0x00404f55:	jb 0x00404f47
0x00404f57:	movl %eax, 0x10(%esp)
0x00404f5b:	addl %eax, %edi
0x00404f5d:	movl 0x10(%esp), %eax
0x00404f61:	movl %edi, %eax
0x00404f63:	jmp 0x00404f70
0x00404efc:	movl %ecx, 0x250(%esi,%ebx,4)
0x00404f03:	testl %ebx, %ebx
0x00404f05:	je 0x00404f37
0x00404e2b:	movl %eax, 0x8(%esi)
0x00404e2e:	leal %ebp, -3(%edi)
0x00404e31:	cmpl %eax, $0x8<UINT8>
0x00404e34:	jb 0x00404e67
0x00404e36:	movl %eax, 0x4(%esi)
0x00404e39:	movl %edx, 0xc(%esi)
0x00404e3c:	shll %edx, $0x8<UINT8>
0x00404e3f:	movb %cl, (%eax)
0x00404e41:	incl %eax
0x00404e42:	movb 0x1c(%esp), %cl
0x00404e46:	movl %ecx, 0x8(%esi)
0x00404e49:	movl 0x4(%esi), %eax
0x00404e4c:	movl %eax, 0x1c(%esp)
0x00404e50:	andl %eax, $0xff<UINT32>
0x00404e55:	addl %ecx, $0xfffffff8<UINT8>
0x00404e58:	orl %edx, %eax
0x00404e5a:	movl %eax, %ecx
0x00404e5c:	cmpl %eax, $0x8<UINT8>
0x00404e5f:	movl 0xc(%esi), %edx
0x00404e62:	movl 0x8(%esi), %ecx
0x00404e65:	jae -49
0x00404e67:	movl %eax, 0x8(%esi)
0x00404e6a:	movl %edi, 0xc(%esi)
0x00404e6d:	movl %ecx, $0x8<UINT32>
0x00404e72:	subl %ecx, %eax
0x00404e74:	addl %eax, %ebp
0x00404e76:	shrl %edi, %cl
0x00404e78:	movl %ecx, $0x18<UINT32>
0x00404e7d:	movl 0x8(%esi), %eax
0x00404e80:	subl %ecx, %ebp
0x00404e82:	andl %edi, $0xffffff<UINT32>
0x00404e88:	shrl %edi, %cl
0x00404e8a:	leal %ecx, 0x130(%esi)
0x00404e90:	call 0x004049b0
0x00404e95:	addl %eax, %ebx
0x00404e97:	leal %ebx, (%eax,%edi,8)
0x00404e9a:	jmp 0x00404ef7
0x00404f07:	movl %edx, 0x250(%esi)
0x00404f0d:	movl 0x250(%esi,%ebx,4), %edx
0x00404f14:	jmp 0x00404f31
0x00404d6f:	leal %ecx, 0xa0(%esi)
0x00404d75:	call 0x004049b0
0x00404d7a:	movl %ecx, 0x8(%esi)
0x00404d7d:	xorl %ebx, %ebx
0x00404d7f:	pushl %esi
0x00404d80:	call 0x00404cf2
0x00404d85:	movb %bl, 0x46c4b6(%eax,%esi)
0x00404d8c:	popl %esi
0x00404d8d:	cmpl %ecx, $0x8<UINT8>
0x00404d90:	jb 0x00404dc4
0x00404dc4:	movl %edi, 0x8(%esi)
0x00404dc7:	movl %edx, 0xc(%esi)
0x00404dca:	movl %ecx, $0x8<UINT32>
0x00404dcf:	subl %ecx, %edi
0x00404dd1:	addl %edi, %ebx
0x00404dd3:	shrl %edx, %cl
0x00404dd5:	movl %ecx, $0x18<UINT32>
0x00404dda:	movl 0x8(%esi), %edi
0x00404ddd:	subl %ecx, %ebx
0x00404ddf:	andl %edx, $0xffffff<UINT32>
0x00404de5:	shrl %edx, %cl
0x00404de7:	xorl %ecx, %ecx
0x00404de9:	pushl %esi
0x00404dea:	call 0x00404cf2
0x00404def:	movb %cl, 0x46c49a(%eax,%esi)
0x00404df6:	popl %esi
0x00404df7:	movl %eax, 0x14(%esp)
0x00404dfb:	addl %ecx, %edx
0x00404dfd:	addl %eax, %ecx
0x00404dff:	movl 0x14(%esp), %eax
0x00404d92:	movl %ecx, 0x4(%esi)
0x00404d95:	movb %dl, (%ecx)
0x00404d97:	incl %ecx
0x00404d98:	movb 0x18(%esp), %dl
0x00404d9c:	movl 0x4(%esi), %ecx
0x00404d9f:	movl %ecx, 0xc(%esi)
0x00404da2:	movl %edx, 0x18(%esp)
0x00404da6:	shll %ecx, $0x8<UINT8>
0x00404da9:	andl %edx, $0xff<UINT32>
0x00404daf:	orl %ecx, %edx
0x00404db1:	movl %edx, 0x8(%esi)
0x00404db4:	addl %edx, $0xfffffff8<UINT8>
0x00404db7:	movl 0xc(%esi), %ecx
0x00404dba:	movl %ecx, %edx
0x00404dbc:	movl 0x8(%esi), %edx
0x00404dbf:	cmpl %ecx, $0x8<UINT8>
0x00404dc2:	jae -50
0x00404f7a:	movl %eax, 0x2c(%esp)
0x00404f7e:	movl (%eax), %edi
0x00404f80:	popl %edi
0x00404f81:	popl %esi
0x00404f82:	popl %ebp
0x00404f83:	movb %al, $0x1<UINT8>
0x00404f85:	popl %ebx
0x00404f86:	addl %esp, $0x14<UINT8>
0x00404f89:	ret $0x8<UINT16>

0x00404718:	testb %al, %al
0x0040471a:	jne 0x00404726
0x00404726:	movl %eax, (%esp)
0x00404729:	addl %esp, $0x354<UINT32>
0x0040472f:	ret $0x10<UINT16>

0x00404101:	movb %bl, $0x0<UINT8>
0x00404103:	cmpb %bl, $0x0<UINT8>
0x00404106:	jne 0x00404155
0x00404108:	incb 0xef(%ebp)
0x0040410e:	pushl %eax
0x0040410f:	pushl %ecx
0x00404110:	pushl %esi
0x00404111:	pushl %ebx
0x00404112:	movl %ecx, %eax
0x00404114:	subl %ecx, $0x5<UINT8>
0x00404117:	movl %esi, 0x144(%ebp)
0x0040411d:	xorl %ebx, %ebx
0x0040411f:	orl %ecx, %ecx
0x00404121:	je 0x00404151
0x00404123:	js 44
0x00404125:	lodsb %al, %ds:(%esi)
0x00404126:	cmpb %al, $0xffffffe8<UINT8>
0x00404128:	je 0x00404134
0x0040412a:	jmp 0x0040412c
0x0040412c:	cmpb %al, $0xffffffe9<UINT8>
0x0040412e:	je 4
0x00404130:	incl %ebx
0x00404131:	decl %ecx
0x00404132:	jmp 0x0040411f
0x00404134:	movl %eax, (%esi)
0x00404136:	jmp 0x00404138
0x00404138:	cmpb (%esi), $0x0<UINT8>
0x0040413b:	jne -13
0x0040413d:	andb %al, $0x0<UINT8>
0x0040413f:	roll %eax, $0x18<UINT8>
0x00404142:	subl %eax, %ebx
0x00404144:	movl (%esi), %eax
0x00404146:	addl %ebx, $0x5<UINT8>
0x00404149:	addl %esi, $0x4<UINT8>
0x0040414c:	subl %ecx, $0x5<UINT8>
0x0040414f:	jmp 0x0040411f
0x00404151:	popl %ebx
0x00404152:	popl %esi
0x00404153:	popl %ecx
0x00404154:	popl %eax
0x00404155:	jmp 0x0040415f
0x0040415f:	movl %ecx, %eax
0x00404161:	movl %edi, (%esi)
0x00404163:	addl %edi, 0x488(%ebp)
0x00404169:	movl %esi, 0x144(%ebp)
0x0040416f:	sarl %ecx, $0x2<UINT8>
0x00404172:	rep movsl %es:(%edi), %ds:(%esi)
0x00404174:	movl %ecx, %eax
0x00404176:	andl %ecx, $0x3<UINT8>
0x00404179:	rep movsb %es:(%edi), %ds:(%esi)
0x0040417b:	popl %esi
0x0040417c:	pushl $0x8000<UINT32>
0x00404181:	pushl $0x0<UINT8>
0x00404183:	pushl 0x144(%ebp)
0x00404189:	call VirtualFree@kernel32.dll
VirtualFree@kernel32.dll: API Node	
0x0040418c:	addl %esi, $0xc<UINT8>
0x0040418f:	cmpl (%esi), $0x0<UINT8>
0x00404192:	jne 0x004040c7
0x00404cc5:	incl %eax
0x00404cc6:	cmpl %eax, $0x8<UINT8>
0x00404cc9:	jb 0x00404cbb
0x00404101:	movb %bl, $0x1<UINT8>
0x00404198:	pushl $0x8000<UINT32>
0x0040419d:	pushl $0x0<UINT8>
0x0040419f:	pushl 0x148(%ebp)
0x004041a5:	call VirtualFree@kernel32.dll
0x004041a8:	movl %ebx, 0x595(%ebp)
0x004041ae:	orl %ebx, %ebx
0x004041b0:	je 0x004041ba
0x004041ba:	movl %edx, 0x488(%ebp)
0x004041c0:	movl %eax, 0x591(%ebp)
0x004041c6:	subl %edx, %eax
0x004041c8:	je 0x00404243
0x00404243:	movl %edx, 0x488(%ebp)
0x00404249:	movl %esi, 0x5a5(%ebp)
0x0040424f:	orl %esi, %esi
0x00404251:	je 0x00404264
0x00404264:	movl %esi, $0x206c<UINT32>
0x00404269:	movl %edx, 0x488(%ebp)
0x0040426f:	addl %esi, %edx
0x00404271:	movl %eax, 0xc(%esi)
0x00404274:	testl %eax, %eax
0x00404276:	je 0x00404389
0x0040427c:	addl %eax, %edx
0x0040427e:	movl %ebx, %eax
0x00404280:	pushl %eax
0x00404281:	call GetModuleHandleA@kernel32.dll
0x00404287:	testl %eax, %eax
0x00404289:	jne 0x00404292
0x00404292:	movl 0x5a9(%ebp), %eax
0x00404298:	movl 0x5ad(%ebp), $0x0<UINT32>
0x004042a2:	movl %edx, 0x488(%ebp)
0x004042a8:	movl %eax, (%esi)
0x004042aa:	testl %eax, %eax
0x004042ac:	jne 0x004042b1
0x004042b1:	addl %eax, %edx
0x004042b3:	addl %eax, 0x5ad(%ebp)
0x004042b9:	movl %ebx, (%eax)
0x004042bb:	movl %edi, 0x10(%esi)
0x004042be:	addl %edi, %edx
0x004042c0:	addl %edi, 0x5ad(%ebp)
0x004042c6:	testl %ebx, %ebx
0x004042c8:	je 0x00404373
0x004042ce:	testl %ebx, $0x80000000<UINT32>
0x004042d4:	jne 4
0x004042d6:	addl %ebx, %edx
0x004042d8:	incl %ebx
0x004042d9:	incl %ebx
0x004042da:	pushl %ebx
0x004042db:	andl %ebx, $0x7fffffff<UINT32>
0x004042e1:	pushl %ebx
0x004042e2:	pushl 0x5a9(%ebp)
0x004042e8:	call GetProcAddress@kernel32.dll
0x004042ee:	testl %eax, %eax
0x004042f0:	popl %ebx
0x004042f1:	jne 0x00404365
0x00404365:	movl (%edi), %eax
0x00404367:	addl 0x5ad(%ebp), $0x4<UINT8>
0x0040436e:	jmp 0x004042a2
0x00404373:	movl (%esi), %eax
0x00404375:	movl 0xc(%esi), %eax
0x00404378:	movl 0x10(%esi), %eax
0x0040437b:	addl %esi, $0x14<UINT8>
0x0040437e:	movl %edx, 0x488(%ebp)
0x00404384:	jmp 0x00404271
0x00404389:	movl %esi, 0x488(%ebp)
0x0040438f:	movl %edi, 0x3c(%esi)
0x00404392:	addl %edi, %esi
0x00404394:	pushl %ecx
0x00404395:	pushl %esp
0x00404396:	pushl %ecx
0x00404397:	pushl %esp
0x00404398:	pushl $0x4<UINT8>
0x0040439a:	pushl 0x54(%edi)
0x0040439d:	pushl %esi
0x0040439e:	call VirtualProtect@kernel32.dll
VirtualProtect@kernel32.dll: API Node	
0x004043a1:	pushl 0x54(%edi)
0x004043a4:	pushl %esi
0x004043a5:	movzwl %ecx, 0x6(%edi)
0x004043a9:	movzwl %eax, 0x14(%edi)
0x004043ad:	leal %edi, -16(%edi,%eax)
0x004043b1:	leal %esi, 0x5c5(%ebp)
0x004043b7:	lodsl %eax, %ds:(%esi)
0x004043b8:	testl %eax, %eax
0x004043ba:	je 0x004043fc
0x004043bc:	addl %edi, $0x28<UINT8>
0x004043bf:	cmpl %eax, 0xc(%edi)
0x004043c2:	loopne -8
0x004043c4:	jne 54
0x004043c6:	incl %ecx
0x004043c7:	pushl %ecx
0x004043c8:	pushl %esi
0x004043c9:	pushl $0x1<UINT8>
0x004043cb:	testb 0x7(%esi), $0xffffffe0<UINT8>
0x004043cf:	je 3
0x004043d1:	shll (%esp)
0x004043d4:	testb 0x7(%esi), $0xffffff80<UINT8>
0x004043d8:	je 0x004043dd
0x004043dd:	testb 0x7(%esi), $0x20<UINT8>
0x004043e1:	je 0x004043e7
0x004043e3:	shll (%esp), $0x4<UINT8>
0x004043e7:	pushl 0x8(%edi)
0x004043ea:	addl %eax, 0x488(%ebp)
0x004043f0:	pushl %eax
0x004043f1:	call VirtualProtect@kernel32.dll
0x004043f4:	popl %ecx
0x004043f5:	lodsl %eax, %ds:(%esi)
0x004043f6:	lodsl %eax, %ds:(%esi)
0x004043f7:	movl 0x24(%edi), %eax
0x004043fa:	loop 0x004043b7
0x004043da:	shll (%esp)
0x004043fc:	call VirtualProtect@kernel32.dll
0x004043ff:	popl %ecx
0x00404400:	movl %eax, $0x1000<UINT32>
0x00404405:	pushl %eax
0x00404406:	addl %eax, 0x488(%ebp)
0x0040440c:	popl %ecx
0x0040440d:	orl %ecx, %ecx
0x0040440f:	movl 0x40e(%ebp), %eax
0x00404415:	popa
0x00404416:	jne 0x00404420
0x00404420:	pushl $0x401000<UINT32>
0x00404425:	ret

0x00401000:	pushl %eax
0x00401001:	pushl %ebx
0x00401002:	leal %eax, 0x403098
0x00401008:	pushl %eax
0x00401009:	call 0x004011f2
0x004011f2:	jmp SetCurrentDirectoryA@kernel32.dll
SetCurrentDirectoryA@kernel32.dll: API Node	
0x0040100e:	leal %eax, 0x40306d
0x00401014:	pushl $0x0<UINT8>
0x00401016:	pushl $0x80<UINT32>
0x0040101b:	pushl $0x2<UINT8>
0x0040101d:	pushl $0x0<UINT8>
0x0040101f:	pushl $0x1<UINT8>
0x00401021:	pushl $0x40000000<UINT32>
0x00401026:	pushl %eax
0x00401027:	call 0x00401180
0x00401180:	jmp CreateFileA@kernel32.dll
CreateFileA@kernel32.dll: API Node	
0x0040102c:	movl %ebx, %eax
0x0040102e:	pushl $0x0<UINT8>
0x00401030:	pushl $0x0<UINT8>
0x00401032:	pushl $0xa<UINT8>
0x00401034:	leal %eax, 0x403008
0x0040103a:	pushl %eax
0x0040103b:	pushl %ebx
0x0040103c:	call 0x004011fe
0x004011fe:	jmp WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x00401041:	pushl %ebx
0x00401042:	call 0x00401174
0x00401174:	jmp CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x00401047:	pushl $0x0<UINT8>
0x00401049:	leal %eax, 0x403080
0x0040104f:	pushl %eax
0x00401050:	leal %eax, 0x403076
0x00401056:	pushl %eax
0x00401057:	call 0x0040117a
0x0040117a:	jmp CopyFileA@kernel32.dll
CopyFileA@kernel32.dll: API Node	
0x0040105c:	leal %eax, 0x403090
0x00401062:	pushl %eax
0x00401063:	leal %eax, 0x40308a
0x00401069:	pushl %eax
0x0040106a:	call 0x00401198
0x00401198:	jmp FindFirstFileA@kernel32.dll
FindFirstFileA@kernel32.dll: API Node	
0x0040106f:	leal %ebx, 0x403090
0x00401075:	pushl %ebx
0x00401076:	pushl %eax
0x00401077:	call 0x0040119e
0x0040119e:	jmp FindNextFileA@kernel32.dll
FindNextFileA@kernel32.dll: API Node	
0x0040107c:	pushl %eax
0x0040107d:	call 0x00401192
0x00401192:	jmp FindClose@kernel32.dll
FindClose@kernel32.dll: API Node	
0x00401082:	leal %eax, 0x40306d
0x00401088:	pushl %eax
0x00401089:	call 0x004011b0
0x004011b0:	jmp GetFileAttributesA@kernel32.dll
GetFileAttributesA@kernel32.dll: API Node	
0x0040108e:	pushl $0x80<UINT32>
0x00401093:	leal %eax, 0x40306d
0x00401099:	pushl %eax
0x0040109a:	call 0x004011f8
0x004011f8:	jmp SetFileAttributesA@kernel32.dll
SetFileAttributesA@kernel32.dll: API Node	
0x0040109f:	leal %eax, 0x40306d
0x004010a5:	pushl %eax
0x004010a6:	call 0x004011b0
0x004010ab:	leal %eax, 0x40306d
0x004010b1:	pushl %eax
0x004010b2:	call 0x00401186
0x00401186:	jmp DeleteFileA@kernel32.dll
DeleteFileA@kernel32.dll: API Node	
0x004010b7:	leal %eax, 0x403060
0x004010bd:	pushl %eax
0x004010be:	call 0x004011ce
0x004011ce:	jmp GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x004010c3:	call 0x004011b6
0x004011b6:	jmp GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x004010c8:	call 0x004011e0
0x004011e0:	jmp GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x004010cd:	leal %ebx, 0x403042
0x004010d3:	pushl $0x64<UINT8>
0x004010d5:	pushl %ebx
0x004010d6:	call 0x004011d4
0x004011d4:	jmp GetSystemDirectoryA@kernel32.dll
GetSystemDirectoryA@kernel32.dll: API Node	
0x004010db:	leal %ebx, 0x403042
0x004010e1:	pushl %ebx
0x004010e2:	pushl $0x64<UINT8>
0x004010e4:	call 0x004011aa
0x004011aa:	jmp GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@kernel32.dll: API Node	
0x004010e9:	leal %ebx, 0x403042
0x004010ef:	pushl $0x64<UINT8>
0x004010f1:	pushl %ebx
0x004010f2:	call 0x004011e6
0x004011e6:	jmp GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x004010f7:	call 0x004011a4
0x004011a4:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004010fc:	leal %eax, 0x403054
0x00401102:	pushl %eax
0x00401103:	call 0x004011da
0x004011da:	jmp GetSystemTime@kernel32.dll
GetSystemTime@kernel32.dll: API Node	
0x00401108:	leal %eax, 0x403022
0x0040110e:	pushl %eax
0x0040110f:	call 0x004011ec
0x004011ec:	jmp LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00401114:	leal %eax, 0x403015
0x0040111a:	pushl %eax
0x0040111b:	call 0x004011ec
0x00401120:	leal %ebx, 0x40302d
0x00401126:	pushl %ebx
0x00401127:	pushl %eax
0x00401128:	call 0x004011c8
0x004011c8:	jmp GetProcAddress@kernel32.dll
0x0040112d:	leal %ebx, 0x403042
0x00401133:	pushl %ebx
0x00401134:	pushl $0x64<UINT8>
0x00401136:	call 0x004011aa
0x0040113b:	pushl $0x64<UINT8>
0x0040113d:	pushl $0x403000<UINT32>
0x00401142:	pushl $0x0<UINT8>
0x00401144:	call 0x004011bc
0x004011bc:	jmp GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00401149:	leal %eax, 0x403008
0x0040114f:	movl %ebx, $0x0<UINT32>
0x00401154:	pushl %ebx
0x00401155:	pushl %eax
0x00401156:	pushl %eax
0x00401157:	pushl %ebx
0x00401158:	call 0x00401204
0x00401204:	jmp MessageBoxA@user32.dll
MessageBoxA@user32.dll: API Node	
0x0040115d:	pushl $0x0<UINT8>
0x0040115f:	call 0x004011c2
0x004011c2:	jmp GetModuleHandleA@kernel32.dll
0x00401164:	addl %eax, $0x116b<UINT32>
0x00401169:	pushl %eax
0x0040116a:	ret

0x0040116b:	addl %eax, %ebx
0x0040116d:	popl %eax
0x0040116e:	call 0x0040118c
0x0040118c:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
