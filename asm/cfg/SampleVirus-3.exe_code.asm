0x00411271:	jmp 0x00415d80
0x00415d80:	pushl %ebp
0x00415d81:	movl %ebp, %esp
0x00415d83:	call 0x004110c3
0x004110c3:	jmp 0x004171e0
0x004171e0:	pushl %ebp
0x004171e1:	movl %ebp, %esp
0x004171e3:	subl %esp, $0x14<UINT8>
0x004171e6:	movl -12(%ebp), $0x0<UINT32>
0x004171ed:	movl -8(%ebp), $0x0<UINT32>
0x004171f4:	cmpl 0x41f000, $0xbb40e64e<UINT32>
0x004171fe:	je 0x0041721f
0x0041721f:	leal %edx, -12(%ebp)
0x00417222:	pushl %edx
0x00417223:	call GetSystemTimeAsFileTime@kernel32.dll
GetSystemTimeAsFileTime@kernel32.dll: API Node	
0x00417229:	movl %eax, -12(%ebp)
0x0041722c:	movl -4(%ebp), %eax
0x0041722f:	movl %ecx, -4(%ebp)
0x00417232:	xorl %ecx, -8(%ebp)
0x00417235:	movl -4(%ebp), %ecx
0x00417238:	call GetCurrentThreadId@kernel32.dll
GetCurrentThreadId@kernel32.dll: API Node	
0x0041723e:	xorl %eax, -4(%ebp)
0x00417241:	movl -4(%ebp), %eax
0x00417244:	call GetCurrentProcessId@kernel32.dll
GetCurrentProcessId@kernel32.dll: API Node	
0x0041724a:	xorl %eax, -4(%ebp)
0x0041724d:	movl -4(%ebp), %eax
0x00417250:	leal %edx, -20(%ebp)
0x00417253:	pushl %edx
0x00417254:	call QueryPerformanceCounter@kernel32.dll
QueryPerformanceCounter@kernel32.dll: API Node	
0x0041725a:	movl %eax, -4(%ebp)
0x0041725d:	xorl %eax, -20(%ebp)
0x00417260:	movl -4(%ebp), %eax
0x00417263:	movl %ecx, -4(%ebp)
0x00417266:	xorl %ecx, -16(%ebp)
0x00417269:	movl -4(%ebp), %ecx
0x0041726c:	movl %edx, -4(%ebp)
0x0041726f:	leal %eax, -4(%ebp)
0x00417272:	xorl %edx, %eax
0x00417274:	movl -4(%ebp), %edx
0x00417277:	cmpl -4(%ebp), $0xbb40e64e<UINT32>
0x0041727e:	jne 0x00417289
0x00417289:	movl %ecx, -4(%ebp)
0x0041728c:	andl %ecx, $0xffff0000<UINT32>
0x00417292:	jne 0x004172a6
0x004172a6:	movl %eax, -4(%ebp)
0x004172a9:	movl 0x41f000, %eax
0x004172ae:	movl %ecx, -4(%ebp)
0x004172b1:	notl %ecx
0x004172b3:	movl 0x41f004, %ecx
0x004172b9:	movl %esp, %ebp
0x004172bb:	popl %ebp
0x004172bc:	ret

0x00415d88:	call 0x00415a00
0x00415a00:	pushl %ebp
0x00415a01:	movl %ebp, %esp
0x00415a03:	pushl $0xfffffffe<UINT8>
0x00415a05:	pushl $0x41e898<UINT32>
0x00415a0a:	pushl $0x4110ff<UINT32>
0x00415a0f:	movl %eax, %fs:0
0x00415a15:	pushl %eax
0x00415a16:	addl %esp, $0xffffffe4<UINT8>
0x00415a19:	pushl %ebx
0x00415a1a:	pushl %esi
0x00415a1b:	pushl %edi
0x00415a1c:	movl %eax, 0x41f000
0x00415a21:	xorl -8(%ebp), %eax
0x00415a24:	xorl %eax, %ebp
0x00415a26:	pushl %eax
0x00415a27:	leal %eax, -16(%ebp)
0x00415a2a:	movl %fs:0, %eax
0x00415a30:	movl -24(%ebp), %esp
0x00415a33:	movl -4(%ebp), $0x0<UINT32>
0x00415a3a:	movl -28(%ebp), $0x0<UINT32>
0x00415a41:	call 0x0041123f
0x0041123f:	jmp 0x004159f0
0x004159f0:	pushl %ebp
0x004159f1:	movl %ebp, %esp
0x004159f3:	movl %eax, %fs:0x18
0x004159f9:	popl %ebp
0x004159fa:	ret

0x00415a46:	movl %eax, 0x4(%eax)
0x00415a49:	movl -32(%ebp), %eax
0x00415a4c:	movl -36(%ebp), $0x0<UINT32>
0x00415a53:	movl %ecx, -32(%ebp)
0x00415a56:	movl %edx, $0x41f744<UINT32>
0x00415a5b:	xorl %eax, %eax
0x00415a5d:	cmpxchgl (%edx), %ecx
0x00415a61:	movl -28(%ebp), %eax
0x00415a64:	cmpl -28(%ebp), $0x0<UINT8>
0x00415a68:	je 0x00415a7d
0x00415a7d:	cmpl 0x41f754, $0x1<UINT8>
0x00415a84:	jne 0x00415a92
0x00415a92:	cmpl 0x41f754, $0x0<UINT8>
0x00415a99:	jne 56
0x00415a9b:	movl 0x41f754, $0x1<UINT32>
0x00415aa5:	pushl $0x41c838<UINT32>
0x00415aaa:	pushl $0x41c428<UINT32>
0x00415aaf:	call 0x00411406
0x00411406:	jmp 0x004173f8
0x004173f8:	jmp _initterm_e@msvcr120d.dll
_initterm_e@msvcr120d.dll: API Node	
0x00415ab4:	addl %esp, $0x8<UINT8>
0x00415ab7:	testl %eax, %eax
0x00415ab9:	je 0x00415ad1
0x00415abb:	movl -44(%ebp), $0xff<UINT32>
0x00415ad1:	jmp 0x00415add
0x00415add:	cmpl 0x41f754, $0x1<UINT8>
0x00415ae4:	jne 28
0x00415ae6:	pushl $0x41c324<UINT32>
0x00415aeb:	pushl $0x41c000<UINT32>
0x00415af0:	call 0x00411154
0x00411154:	jmp 0x004173fe
0x004173fe:	jmp _initterm@msvcr120d.dll
_initterm@msvcr120d.dll: API Node	
0x00415af5:	addl %esp, $0x8<UINT8>
0x00415af8:	movl 0x41f754, $0x2<UINT32>
0x00415b02:	cmpl 0x41f754, $0x2<UINT8>
0x00415b09:	je 0x00415b32
0x00415b32:	cmpl -36(%ebp), $0x0<UINT8>
0x00415b36:	jne 9
0x00415b38:	xorl %edx, %edx
0x00415b3a:	movl %eax, $0x41f744<UINT32>
0x00415b3f:	xchgl (%eax), %edx
0x00415b41:	cmpl 0x41f758, $0x0<UINT8>
0x00415b48:	je 0x00415b67
0x00415b67:	pushl $0x1<UINT8>
0x00415b69:	call _CrtSetCheckCount@msvcr120d.dll
_CrtSetCheckCount@msvcr120d.dll: API Node	
0x00415b6f:	addl %esp, $0x4<UINT8>
0x00415b72:	movl %ecx, 0x420100
0x00415b78:	movl %edx, 0x41f348
0x00415b7e:	movl (%ecx), %edx
0x004110ff:	jmp 0x00415db0
0x00415db0:	pushl %ebp
0x00415db1:	movl %ebp, %esp
0x00415db3:	movl %eax, 0x14(%ebp)
0x00415db6:	pushl %eax
0x00415db7:	movl %ecx, 0x10(%ebp)
0x00415dba:	pushl %ecx
0x00415dbb:	movl %edx, 0xc(%ebp)
0x00415dbe:	pushl %edx
0x00415dbf:	movl %eax, 0x8(%ebp)
0x00415dc2:	pushl %eax
0x00415dc3:	pushl $0x41103c<UINT32>
0x00415dc8:	pushl $0x41f000<UINT32>
0x00415dcd:	call 0x004113a2
0x004113a2:	jmp 0x00417404
0x00417404:	jmp _except_handler4_common@msvcr120d.dll
_except_handler4_common@msvcr120d.dll: API Node	
0x00415dd2:	addl %esp, $0x18<UINT8>
0x00415dd5:	popl %ebp
0x00415dd6:	ret

0x00415b80:	movl %eax, 0x41f348
0x00415b85:	pushl %eax
0x00415b86:	movl %ecx, 0x41f344
0x00415b8c:	pushl %ecx
0x00415b8d:	movl %edx, 0x41f340
0x00415b93:	pushl %edx
0x00415b94:	call 0x004112da
0x004112da:	jmp 0x00412b50
0x00412b50:	pushl %ebp
0x00412b51:	movl %ebp, %esp
0x00412b53:	subl %esp, $0xc0<UINT32>
0x00412b59:	pushl %ebx
0x00412b5a:	pushl %esi
0x00412b5b:	pushl %edi
0x00412b5c:	leal %edi, -192(%ebp)
0x00412b62:	movl %ecx, $0x30<UINT32>
0x00412b67:	movl %eax, $0xcccccccc<UINT32>
0x00412b6c:	rep stosl %es:(%edi), %eax
0x00412b6e:	movl %eax, $0x2<UINT32>
0x00412b73:	movl %ecx, $0x3<UINT32>
0x00412b78:	shll %eax, %cl
0x00412b7a:	popl %edi
0x00412b7b:	popl %esi
0x00412b7c:	popl %ebx
0x00412b7d:	addl %esp, $0xc0<UINT32>
0x00412b83:	cmpl %ebp, %esp
0x00412b85:	call 0x004112d5
0x004112d5:	jmp 0x00415750
0x00415750:	jne 1
0x00415752:	ret

0x00412b8a:	movl %esp, %ebp
0x00412b8c:	popl %ebp
0x00412b8d:	ret

0x00415b99:	addl %esp, $0xc<UINT8>
0x00415b9c:	movl 0x41f338, %eax
0x00415ba1:	cmpl 0x41f33c, $0x0<UINT8>
0x00415ba8:	jne 12
0x00415baa:	movl %eax, 0x41f338
0x00415baf:	pushl %eax
0x00415bb0:	call exit@msvcr120d.dll
exit@msvcr120d.dll: Exit Node	
0x00415ac2:	movl -4(%ebp), $0xfffffffe<UINT32>
0x00415ac9:	movl %eax, -44(%ebp)
0x00415acc:	jmp 0x00415c25
0x00415c25:	movl %ecx, -16(%ebp)
0x00415c28:	movl %fs:0, %ecx
0x00415c2f:	popl %ecx
0x00415c30:	popl %edi
0x00415c31:	popl %esi
0x00415c32:	popl %ebx
0x00415c33:	movl %esp, %ebp
0x00415c35:	popl %ebp
0x00415c36:	ret

0x00415d8d:	popl %ebp
0x00415d8e:	ret

0x7c817067: Exit Node	
